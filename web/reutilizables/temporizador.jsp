<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- ================================
     TEMPORIZADOR DE INACTIVIDAD (JSP)
     Descripción:
     Este componente controla el tiempo de inactividad del usuario.
     Si el usuario no realiza ninguna acción (clic, movimiento, scroll, etc.)
     durante 60 segundos, el sistema redirige automáticamente a login.jsp.
     Incluye un contador visible con estilo acorde al diseño general (rojo sangre + dorado).
================================ -->

<style>
    /* ======== ESTILO DEL CONTENEDOR DEL TEMPORIZADOR ======== */
    .timer-container {
        position: fixed;
        bottom: 20px;                    /* Posición en la esquina inferior derecha */
        right: 20px;
        background: linear-gradient(135deg, #8B0000, #B22222); /* Degradado rojo sangre */
        color: #fff;
        border: 2px solid #ffc107;       /* Borde dorado */
        border-radius: 12px;
        box-shadow: 0 0 10px rgba(178, 34, 34, 0.6);
        padding: 12px 20px;
        text-align: center;
        font-family: 'Segoe UI', sans-serif;
        z-index: 1000;                   /* Asegura que quede encima del contenido */
        transition: all 0.3s ease-in-out;
    }

    /* Efecto visual al pasar el mouse sobre el temporizador */
    .timer-container:hover {
        transform: translateY(-3px);
        box-shadow: 0 0 14px rgba(255, 193, 7, 0.8);
    }

    /* Estilo del número del temporizador (minutos:segundos) */
    .timer {
        font-size: 1.6rem;
        font-weight: 700;
        letter-spacing: 1px;
        color: #fff;
    }

    /* Texto descriptivo debajo del contador */
    .timer-label {
        font-size: 0.9rem;
        color: #ffeeba; /* Dorado claro */
        margin-top: 4px;
        letter-spacing: 0.5px;
    }

    /* Cambio de color cuando el tiempo está por agotarse */
    .timer.warning {
        color: #ffb3b3;
        text-shadow: 0 0 8px rgba(255, 50, 50, 0.7);
    }
</style>

<!-- Contenedor visible del temporizador -->
<div class="timer-container">
    <div class="timer" id="timer">05:00</div>
    <div class="timer-label">Tiempo de inactividad</div>
</div>

<script>
    /* =========================================
       LÓGICA DEL TEMPORIZADOR DE INACTIVIDAD
       ========================================= */

    let inactivityTime = 300; // 5 minutos
    let timer;

    function resetTimer() {
        clearTimeout(timer);
        inactivityTime = 300;
        updateTimerDisplay();
        startTimer();
    }

    function startTimer() {
        timer = setTimeout(function() {
            if (inactivityTime > 0) {
                inactivityTime--;
                updateTimerDisplay();
                startTimer();
            } else {
                window.location.href = 'login.jsp'; // 🔸 Redirección tras inactividad
            }
        }, 1000);
    }

    function updateTimerDisplay() {
        const minutes = Math.floor(inactivityTime / 60);
        const seconds = inactivityTime % 60;
        const timerElement = document.getElementById('timer');

        timerElement.textContent =
            (minutes < 10 ? '0' + minutes : minutes) + ':' +
            (seconds < 10 ? '0' + seconds : seconds);

        if (inactivityTime <= 10) {
            timerElement.classList.add('warning');
        } else {
            timerElement.classList.remove('warning');
        }
    }

    // Escucha actividad en la ventana principal
    const events = ['mousedown', 'mousemove', 'keypress', 'scroll', 'touchstart'];
    events.forEach(function(name) {
        document.addEventListener(name, resetTimer, true);
    });

    // 🔹 También escucha actividad enviada desde el iframe
    window.addEventListener("message", function(event) {
        if (event.data === "user-active") {
            resetTimer();
        }
    });

    resetTimer();
</script>