document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.action-btn').forEach(function(button) {
        button.addEventListener('click', function() {
            markAsDone(button);
        });
    });
});

function markAsDone(button) {
    const appointmentId = parseInt(button.getAttribute('data-id'), 10);

    fetch(`/appointments/${appointmentId}/mark-as-done`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content')
        }
    })
    .then(response => {
        if (response.ok) {
            window.location.reload(); // This will refresh the current page
        } else {
            console.error('Failed to mark as done');
        }
    })
    .catch(error => console.error('Error:', error));
}
