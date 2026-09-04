// Factoría de páginas CRUD: tabla + buscador + modal de creación/edición + eliminación.
import { apiGet, apiPost, apiPut, apiDelete } from './api';
import { esc, toast, openModal, badge, fmtDate, fullName } from './ui';

const TYPE_MAP = {
  text: 'text',
  number: 'number',
  date: 'date',
  email: 'email',
  tel: 'tel',
  password: 'password',
  textarea: 'textarea',
  select: 'select',
};

function fieldValue(item, f) {
  if (!item) return '';
  const v = item[f.name];
  if (v == null) return '';
  let s = String(v);
  // Los inputs de tipo date solo aceptan YYYY-MM-DD
  if (f.type === 'date' && s.includes('T')) s = s.slice(0, 10);
  return s;
}

function fieldHtml(f, value = '') {
  const label = `<label class="label">${esc(f.label)}${f.required ? ' *' : ''}</label>`;
  const attrs = `name="${esc(f.name)}" ${f.required ? 'required' : ''} ${f.placeholder ? `placeholder="${esc(f.placeholder)}"` : ''}`;

  if (f.type === 'textarea') {
    return `<div class="${f.span ? 'sm:col-span-2' : ''}">${label}<textarea ${attrs} rows="3" class="input resize-none">${esc(value)}</textarea></div>`;
  }
  if (f.type === 'select') {
    const opts = (f.options || [])
      .map((o) => {
        const ov = typeof o === 'object' ? o.value : o;
        const ol = typeof o === 'object' ? o.label : o;
        return `<option value="${esc(ov)}" ${String(ov) === String(value) ? 'selected' : ''}>${esc(ol)}</option>`;
      })
      .join('');
    return `<div>${label}<select ${attrs} class="input">${f.placeholder ? `<option value="">${esc(f.placeholder)}</option>` : ''}${opts}</select></div>`;
  }
  return `<div>${label}<input type="${TYPE_MAP[f.type] || 'text'}" ${attrs} ${f.step ? `step="${f.step}"` : ''} value="${esc(value)}" class="input" /></div>`;
}

export function createCrudPage(config) {
  const {
    root,
    endpoint,
    title,
    description = '',
    columns = [],
    fields = [],
    searchKeys = [],
    emptyText = 'Aún no hay registros.',
    createLabel = 'Nuevo registro',
    searchPlaceholder = 'Buscar…',
    showActions = true,
    extraActions = [],
  } = config;

  const el = document.querySelector(root);
  if (!el) return;

  let items = [];
  let query = '';

  el.innerHTML = `
    <div class="card fade-in overflow-hidden">
      <div class="flex flex-wrap items-center justify-between gap-4 border-b border-slate-100 px-6 py-5">
        <div>
          <h2 class="text-xl font-extrabold text-ink">${esc(title)}</h2>
          ${description ? `<p class="mt-0.5 text-sm text-ink-soft">${esc(description)}</p>` : ''}
        </div>
        <div class="flex flex-wrap items-center gap-3">
          <div class="relative">
            <svg class="pointer-events-none absolute top-1/2 left-3 h-4 w-4 -translate-y-1/2 text-slate-400" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2"><path stroke-linecap="round" stroke-linejoin="round" d="M21 21l-4.35-4.35M17 10.5a6.5 6.5 0 11-13 0 6.5 6.5 0 0113 0z"/></svg>
            <input id="crud-search" type="search" placeholder="${esc(searchPlaceholder)}" class="input w-52 pl-9" />
          </div>
          <button id="crud-new" class="btn-primary">
            <svg class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2.5"><path stroke-linecap="round" stroke-linejoin="round" d="M12 4.5v15m7.5-7.5h-15"/></svg>
            ${esc(createLabel)}
          </button>
        </div>
      </div>
      <div id="crud-body" class="px-6 pt-5 pb-6"></div>
    </div>`;

  const body = el.querySelector('#crud-body');
  const search = el.querySelector('#crud-search');
  const newBtn = el.querySelector('#crud-new');

  function renderSkeleton() {
    const cols = showActions ? [...columns, { label: 'Acciones' }] : columns;
    const rows = Array.from({ length: 5 }, () => `<tr>${cols.map(() => '<td class="td"><div class="skeleton h-4 w-full"></div></td>').join('')}</tr>`).join('');
    body.innerHTML = `
      <div class="overflow-x-auto"><table class="w-full min-w-[560px]">
        <thead><tr>${cols.map((c) => `<th class="th">${esc(c.label)}</th>`).join('')}</tr></thead>
        <tbody>${rows}</tbody>
      </table></div>`;
  }

  function renderEmpty(text) {
    body.innerHTML = `
      <div class="flex flex-col items-center gap-3 py-14 text-center">
        <div class="flex size-14 items-center justify-center rounded-2xl bg-brand-50 text-brand-400">
          <svg class="h-7 w-7" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="1.5"><path stroke-linecap="round" stroke-linejoin="round" d="M3.75 12h16.5m-16.5 0a9 9 0 1116.5 0m-16.5 0a9 9 0 0016.5 0"/></svg>
        </div>
        <p class="text-sm font-semibold text-ink-soft">${esc(text)}</p>
      </div>`;
  }

  function renderError(msg) {
    body.innerHTML = `
      <div class="flex flex-col items-center gap-3 rounded-2xl border border-rose-100 bg-rose-50/60 py-12 text-center">
        <p class="text-sm font-semibold text-rose-700">No se pudo conectar con el servidor.</p>
        <p class="max-w-md text-xs text-rose-500">${esc(msg)}</p>
        <p class="max-w-md text-xs text-ink-soft">Verifica que el backend esté corriendo en <code class="rounded bg-slate-100 px-1.5 py-0.5 font-mono">http://localhost:8080</code></p>
        <button class="btn-soft mt-1" id="crud-retry">Reintentar</button>
      </div>`;
    el.querySelector('#crud-retry')?.addEventListener('click', load);
  }

  function renderTable() {
    const filtered = items.filter((it) => {
      if (!query) return true;
      const hay = searchKeys.map((k) => String(it[k] ?? '')).join(' ').toLowerCase();
      return hay.includes(query);
    });

    if (!filtered.length) {
      renderEmpty(query ? `Sin resultados para «${query}»` : emptyText);
      return;
    }

    const rows = filtered
      .map((raw) => {
        const it = raw;
        return `<tr class="border-t border-slate-50 transition hover:bg-brand-50/40">
          ${columns
            .map((c) => {
              const v = c.render ? c.render(it, raw) : esc(it[c.key] ?? '—');
              return `<td class="td">${v}</td>`;
            })
            .join('')}
          ${showActions ? `
          <td class="td">
            <div class="flex items-center gap-1">
              ${extraActions
                .map(
                  (a, i) => `
              <button data-extra="${i}" data-rid="${esc(it.id)}" class="${a.cls || 'btn-icon'}" title="${esc(a.title)}">
                ${a.svg || ''}
              </button>`,
                )
                .join('')}
              <button data-edit="${esc(it.id)}" class="btn-icon" title="Editar">
                <svg class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2"><path stroke-linecap="round" stroke-linejoin="round" d="M16.862 4.487l1.687-1.688a1.875 1.875 0 112.652 2.652L10.582 16.07a4.5 4.5 0 01-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 011.13-1.897l8.932-8.931zm0 0L19.5 7.125"/></svg>
              </button>
              <button data-del="${esc(it.id)}" class="btn-icon-danger" title="Eliminar">
                <svg class="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2"><path stroke-linecap="round" stroke-linejoin="round" d="M14.74 9l-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 01-2.244 2.077H8.084a2.25 2.25 0 01-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 00-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 013.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 00-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 00-7.5 0"/></svg>
              </button>
            </div>
          </td>` : ''}
        </tr>`;
      })
      .join('');

    body.innerHTML = `
      <div class="overflow-x-auto rounded-xl border border-slate-100">
        <table class="w-full min-w-[560px]">
          <thead><tr class="bg-slate-50/80">${columns.map((c) => `<th class="th">${esc(c.label)}</th>`).join('')}${showActions ? '<th class="th">Acciones</th>' : ''}</tr></thead>
          <tbody>${rows}</tbody>
        </table>
      </div>
      <p class="mt-3 text-xs font-semibold text-ink-soft">${filtered.length} registro${filtered.length !== 1 ? 's' : ''}</p>`;

    body.querySelectorAll('[data-edit]').forEach((btn) => {
      btn.addEventListener('click', () => {
        const item = items.find((x) => String(x.id) === btn.dataset.edit);
        if (item) openFormModal(item);
      });
    });
    body.querySelectorAll('[data-extra]').forEach((btn) => {
      btn.addEventListener('click', () => {
        const item = items.find((x) => String(x.id) === btn.dataset.rid);
        const action = extraActions[Number(btn.dataset.extra)];
        if (item && action) action.onClick(item, load);
      });
    });
    body.querySelectorAll('[data-del]').forEach((btn) => {
      btn.addEventListener('click', () => {
        const item = items.find((x) => String(x.id) === btn.dataset.del);
        if (item) confirmDelete(item);
      });
    });
  }

  async function load() {
    renderSkeleton();
    try {
      items = await apiGet(endpoint);
      renderTable();
    } catch (err) {
      renderError(err.message);
    }
  }

  async function openFormModal(item = null) {
    const editing = Boolean(item);
    // Resolver opciones async (selects que se cargan de otras APIs)
    const resolved = await Promise.all(
      fields.map(async (f) => ({
        ...f,
        options: typeof f.options === 'function' ? await f.options() : f.options,
      })),
    );
    const bodyHtml = resolved.map((f) => fieldHtml(f, fieldValue(item, f))).join('') || '<p class="text-sm text-ink-soft">Sin campos.</p>';
    openModal({
      title: editing ? `Editar: ${title}` : `Nuevo: ${title}`,
      body: bodyHtml,
      submitLabel: editing ? 'Guardar cambios' : 'Guardar',
      onSubmit: async (form) => {
        const payload = {};
        for (const f of resolved) {
          const input = form.get(f.name);
          if (input === null) continue;
          let v = String(input).trim();
          if (v === '') continue;
          if (f.type === 'number') {
            v = Number(v);
          } else if (f.type === 'select' && typeof f.options === 'object' && Array.isArray(f.options)) {
            // Coercer valores numéricos en selects (ids de otras tablas)
            const matched = f.options.find((o) => String(typeof o === 'object' ? o.value : o) === String(v));
            if (matched && typeof matched === 'object' && typeof matched.value === 'number') v = Number(v);
          }
          payload[f.name] = v;
        }
        if (editing) {
          await apiPut(`${endpoint}/${item.id}`, payload);
          toast('Registro actualizado correctamente', 'success');
        } else {
          await apiPost(endpoint, payload);
          toast('Registro creado correctamente', 'success');
        }
        load();
      },
    });
  }

  function confirmDelete(item) {
    openModal({
      title: 'Eliminar registro',
      size: 'max-w-md',
      submitLabel: 'Eliminar',
      danger: true,
      body: `
        <div class="sm:col-span-2 py-2 text-center">
          <div class="mx-auto flex size-12 items-center justify-center rounded-full bg-rose-50 text-rose-600">
            <svg class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2"><path stroke-linecap="round" stroke-linejoin="round" d="M14.74 9l-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 01-2.244 2.077H8.084a2.25 2.25 0 01-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 00-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 013.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 00-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 00-7.5 0"/></svg>
          </div>
          <p class="mt-3 text-sm font-semibold text-ink">¿Seguro que deseas eliminar este registro?</p>
          <p class="mt-1 text-xs text-ink-soft">ID #${esc(item.id)} · Esta acción no se puede deshacer.</p>
        </div>`,
      onSubmit: async () => {
        await apiDelete(`${endpoint}/${item.id}`);
        toast('Registro eliminado correctamente', 'success');
        load();
      },
    });
  }

  search.addEventListener('input', (e) => {
    query = e.target.value.trim().toLowerCase();
    renderTable();
  });
  newBtn.addEventListener('click', () => openFormModal());

  load();
}

// Helpers de render comunes
export { badge, fmtDate, fullName };