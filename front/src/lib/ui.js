// Utilidades de UI: escape, toasts, modales, badges y formatos.

export const esc = (v) =>
  String(v ?? '').replace(/[&<>"']/g, (c) => ({ '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#39;' }[c]));

export function toast(message, type = 'success') {
  let box = document.getElementById('toast-box');
  if (!box) {
    box = document.createElement('div');
    box.id = 'toast-box';
    box.className = 'fixed bottom-6 right-6 z-[100] flex flex-col gap-2';
    document.body.appendChild(box);
  }

  const colors = {
    success: 'bg-emerald-600',
    error: 'bg-rose-600',
    info: 'bg-brand-700',
  };
  const el = document.createElement('div');
  el.className = `${colors[type] || colors.info} modal-anim pointer-events-auto flex items-center gap-2 rounded-xl px-4 py-3 text-sm font-semibold text-white shadow-xl`;
  el.innerHTML = `
    <svg class="h-4 w-4 shrink-0" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2.5">
      ${type === 'error'
        ? '<path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12"/>'
        : '<path stroke-linecap="round" stroke-linejoin="round" d="M4.5 12.75l6 6 9-13.5"/>'}
    </svg>
    <span>${esc(message)}</span>`;
  box.appendChild(el);

  // Cada toast gestiona su propio temporizador de forma independiente
  setTimeout(() => {
    el.style.transition = 'opacity .3s, transform .3s';
    el.style.opacity = '0';
    el.style.transform = 'translateY(8px)';
    setTimeout(() => el.remove(), 320);
  }, 3200);
}

// ---------- Modal ----------
export function openModal({ title, body, submitLabel = 'Guardar', onSubmit, size = 'max-w-xl', danger = false }) {
  const overlay = document.createElement('div');
  overlay.className = 'fixed inset-0 z-[90] flex items-start justify-center overflow-y-auto bg-brand-950/50 p-4 backdrop-blur-sm sm:items-center';
  overlay.innerHTML = `
    <div class="card modal-anim my-8 w-full ${size}">
      <div class="flex items-center justify-between border-b border-slate-100 px-6 py-4">
        <h3 class="text-lg font-extrabold text-ink">${esc(title)}</h3>
        <button data-close class="rounded-lg p-1.5 text-slate-400 transition hover:bg-slate-100 hover:text-ink" aria-label="Cerrar">
          <svg class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2"><path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12"/></svg>
        </button>
      </div>
      <form class="px-6 py-5">
        <div class="grid grid-cols-1 gap-4 sm:grid-cols-2">${body}</div>
        <div class="mt-6 flex items-center justify-end gap-3 border-t border-slate-100 pt-4">
          <button type="button" data-close class="btn-ghost">Cancelar</button>
          <button type="submit" class="${danger ? 'btn-danger' : 'btn-primary'}">${esc(submitLabel)}</button>
        </div>
      </form>
    </div>`;

  document.body.appendChild(overlay);
  document.body.style.overflow = 'hidden';

  const close = () => {
    overlay.remove();
    document.body.style.overflow = '';
  };

  overlay.querySelectorAll('[data-close]').forEach((b) => b.addEventListener('click', close));
  overlay.addEventListener('mousedown', (e) => {
    if (e.target === overlay) close();
  });
  overlay.querySelector('form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const btn = overlay.querySelector('[type="submit"]');
    btn.disabled = true;
    try {
      await onSubmit(new FormData(overlay.querySelector('form')));
      close();
    } catch (err) {
      toast(err.message || 'No se pudo guardar', 'error');
    } finally {
      btn.disabled = false;
    }
  });

  return close;
}

// ---------- Badges por estado ----------
const BADGE = {
  green: 'bg-emerald-50 text-emerald-700 ring-emerald-600/20',
  amber: 'bg-amber-50 text-amber-700 ring-amber-600/25',
  rose: 'bg-rose-50 text-rose-700 ring-rose-600/20',
  sky: 'bg-sky-50 text-sky-700 ring-sky-600/20',
  violet: 'bg-brand-50 text-brand-700 ring-brand-600/20',
  slate: 'bg-slate-100 text-slate-600 ring-slate-500/20',
  pink: 'bg-pink-50 text-pink-700 ring-pink-600/20',
};

const BADGE_COLOR = {
  ACTIVO: 'green', ACTIVA: 'green', PRESENTE: 'green', APROBADO: 'green', DISPONIBLE: 'green',
  PAGADO: 'green', REALIZADA: 'green', GRADUADO: 'green', RESUELTO: 'green', VALIDADO: 'green',
  PENDIENTE: 'amber', PROGRAMADA: 'amber', TARDE: 'amber', SUSPENDIDO: 'amber', MANTENIMIENTO: 'amber',
  PLANIFICADO: 'amber', TRASLADO: 'amber', VENCIDO: 'amber', MODERADO: 'amber', PUBLICADO: 'amber',
  INACTIVO: 'rose', INACTIVA: 'rose', AUSENTE: 'rose', RETIRADO: 'rose', REPROBADO: 'rose',
  CANCELADA: 'rose', CANCELADO: 'rose', CERRADO: 'rose', RECHAZADO: 'rose', GRAVE: 'rose', ABIERTO: 'rose',
  FINALIZADO: 'sky', FINALIZADA: 'sky', ENTREGADA: 'sky', CALIFICADA: 'sky', CONTINUIDAD: 'sky',
  JUSTIFICADA: 'sky', VIRTUAL: 'sky', OPTATIVA: 'sky', LABORATORIO: 'sky', MASCULINO: 'sky',
  NUEVO: 'violet', REINGRESO: 'violet', MATUTINO: 'violet', MIXTO: 'violet', OBLIGATORIA: 'violet',
  PRESENCIAL: 'violet', HIBRIDA: 'violet', AULA: 'violet', BIBLIOTECA: 'violet', OTRO: 'slate',
  NOCTURNO: 'slate', AUDITORIO: 'amber', COMPUTO: 'sky', TALLER: 'amber', EXTRACURRICULAR: 'rose',
  FEMENINO: 'pink', LEVE: 'sky', EXPIRADO: 'slate', BORRADOR: 'slate', PUBLICADA: 'sky', CERRADA: 'rose',
  ACADEMICO: 'violet', DEPORTIVO: 'green', CULTURAL: 'pink', REUNION: 'sky', FERIADO: 'amber', EVENTO: 'violet',
  INFO: 'sky', AVISO: 'amber', ALERTA: 'rose', ACADEMICA: 'violet', PAGO: 'green', ASISTENCIA: 'sky',
  EFECTIVO: 'green', TRANSFERENCIA: 'sky', TARJETA: 'violet', CHEQUE: 'amber',
  DUI: 'violet', NIE: 'sky', PARTIDA_NACIMIENTO: 'green', CERTIFICADO: 'amber', CONSTANCIA: 'violet',
  FOTOGRAFIA: 'pink', BORRADOR_PUB: 'slate',
};

export function badge(value) {
  const color = BADGE_COLOR[value] || 'slate';
  return `<span class="badge ring-1 ring-inset ${BADGE[color]}">${esc(value ?? '—')}</span>`;
}

// ---------- Formatos ----------
export function fmtDate(iso) {
  if (!iso) return '—';
  const d = new Date(iso);
  if (Number.isNaN(d.getTime())) return esc(iso);
  return d.toLocaleDateString('es-SV', { day: '2-digit', month: 'short', year: 'numeric' });
}

export function initials(nombre = '', apellido = '') {
  return esc(((nombre[0] || '') + (apellido[0] || '')).toUpperCase() || '?');
}

export function fullName(r) {
  return esc(`${r.nombres ?? ''} ${r.apellidos ?? ''}`.trim() || '—');
}

export const AVATAR_COLORS = [
  'from-brand-500 to-flare-500',
  'from-sky-500 to-brand-500',
  'from-flare-500 to-amber-400',
  'from-emerald-500 to-sky-500',
  'from-amber-400 to-flare-500',
];