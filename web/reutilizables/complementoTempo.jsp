<script>
    // Detecta cualquier interacción dentro del iframe y notifica al padre
    const eventos = ['mousedown', 'mousemove', 'keypress', 'scroll', 'touchstart'];
    eventos.forEach(evento => {
        document.addEventListener(evento, () => {
            window.parent.postMessage("user-active", "*");
        });
    });
</script>
