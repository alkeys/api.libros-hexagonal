// Cliente API del sistema escolar.
// La URL base se puede sobreescribir con PUBLIC_API_URL (ver .env / astro config).
const API_BASE = import.meta.env.PUBLIC_API_URL || 'http://localhost:8080/api/v1';

const SESSION_KEY = 'escuela_session';

function getToken() {
  try {
    const raw = localStorage.getItem(SESSION_KEY);
    return raw ? JSON.parse(raw).token : null;
  } catch {
    return null;
  }
}

async function request(path, options = {}) {
  const token = getToken();
  const headers = { 'Content-Type': 'application/json', ...(options.headers || {}) };
  if (token) headers['Authorization'] = `Bearer ${token}`;

  const res = await fetch(`${API_BASE}${path}`, { ...options, headers });

  // Sesión expirada o inválida: limpiar y volver al login (excepto en el propio login)
  if (res.status === 401 && !path.includes('/usuarios/login')) {
    try { localStorage.removeItem(SESSION_KEY); } catch { /* noop */ }
    if (!window.location.pathname.startsWith('/login')) {
      window.location.replace('/login');
    }
    throw new Error('Tu sesión expiró. Inicia sesión de nuevo.');
  }

  if (!res.ok) {
    let message = `Error ${res.status}`;
    try {
      const data = await res.json();
      message = data.message || data.detail || data.error || message;
    } catch {
      /* body vacío */
    }
    throw new Error(message);
  }

  if (res.status === 204) return null;
  return res.json();
}

export const apiGet = (path) => request(path);
export const apiPost = (path, body) => request(path, { method: 'POST', body: JSON.stringify(body) });
export const apiPut = (path, body) => request(path, { method: 'PUT', body: JSON.stringify(body) });
export const apiDelete = (path) => request(path, { method: 'DELETE' });

export { API_BASE };