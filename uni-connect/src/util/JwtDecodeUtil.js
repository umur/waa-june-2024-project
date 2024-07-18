export function decodeBase64Url(base64Url) {
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(atob(base64).split('').map(c => {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
}

export function decodeToken(token) {
    const [header, payload, signature] = token.split('.');
    if (!payload) {
        throw new Error('Invalid token');
    }
    return decodeBase64Url(payload);
}