window.setInterval(function(){
    fetch('http://localhost:8080/api/dateTimeSimu')
    .then(resp => resp.json())
    .then(simEtat => {
        document.querySelector("#dateTimeSim").innerHTML = simEtat.time;
    }
    )
}, 6000);

