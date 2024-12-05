<#import "parts/common.ftlh" as c>


<@c.page>
    <div class="container text-center">
        <div class="col-md-8 mx-auto">
            <h1 class="text-center my-4">Схема зала: ${hall.name}</h1>
        </div>
        <div class="hall d-flex flex-column align-items-center mb-4">
            <#list 1..hall.rows as row>
                <div class="row w-100 justify-content-center mb-2">
                    <div class="col-auto d-flex flex-nowrap justify-content-center">
                        <#list 1..hall.seatsPerRow as place>
                            <#assign seatKey = row + "-" + place>
                            <#if seatStatus[seatKey]?? && seatStatus[seatKey]>
                                <!-- Занятое место -->
                                <button
                                        type="button"
                                        class="seat btn btn-danger custom-seat mx-1"
                                        data-seat="${seatKey}"
                                        data-bs-toggle="tooltip"
                                        data-bs-placement="top"
                                        title="Место ${seatKey}"
                                        disabled>
                                    <span class="material-symbols-outlined">chair</span>
                                </button>
                            <#else>
                                <!-- Доступное место -->
                                <button
                                        type="button"
                                        class="seat btn btn-success custom-seat mx-1"
                                        data-seat="${seatKey}"
                                        data-bs-toggle="tooltip"
                                        data-bs-placement="top"
                                        title="Место ${seatKey}">
                                    <span class="material-symbols-outlined">chair</span>
                                </button>
                            </#if>
                        </#list>
                    </div>
                </div>
            </#list>
        </div>
        <form id="confirmationForm" class="text-center" method="post" action="/order/confirm">
            <input type="hidden" name="sessionId" value="${session.id?c}">
            <input type="hidden" name="selectedSeats" id="selectedSeats">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <h2 class="my-3">Выбранные места:</h2>
            <ul id="selectedSeatsList" class="list-unstyled"></ul>
            <p>Общая стоимость: <span id="totalCost">0</span> ₽</p>
            <button class="btn btn-primary my-3" type="submit" id="confirmButton" disabled>Подтвердить</button>
        </form>
    </div>
</@c.page>



<script>
    document.addEventListener('DOMContentLoaded', () => {
        const selectedSeats = new Set();
        const seatButtons = document.querySelectorAll('.seat');
        const selectedSeatsList = document.getElementById('selectedSeatsList');
        const totalCost = document.getElementById('totalCost');
        const confirmButton = document.getElementById('confirmButton');
        const sessionPrice = ${session.price?c};


        const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
        const tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
            return new bootstrap.Tooltip(tooltipTriggerEl);
        });

        seatButtons.forEach(btn => {

            if (btn.disabled) {
                return;
            }

            btn.addEventListener('click', () => {
                const seat = btn.getAttribute('data-seat');

                if (selectedSeats.has(seat)) {

                    selectedSeats.delete(seat);
                    btn.classList.remove('btn-warning');
                    btn.classList.add('btn-success');
                    btn.innerHTML = '<span class="material-symbols-outlined">chair</span>';
                } else {

                    selectedSeats.add(seat);
                    btn.classList.remove('btn-success');
                    btn.classList.add('btn-warning');
                    btn.innerHTML = '<span class="material-symbols-outlined">chair</span>';
                }

                updateUI();
            });
        });

        function updateUI() {

            selectedSeatsList.innerHTML = '';
            selectedSeats.forEach(seat => {
                const li = document.createElement('li');
                li.textContent = seat;
                selectedSeatsList.appendChild(li);
            });


            const cost = selectedSeats.size * sessionPrice;
            totalCost.textContent = cost;


            document.getElementById('selectedSeats').value = Array.from(selectedSeats).join(',');
            confirmButton.disabled = selectedSeats.size === 0;
        }
    });
</script>
