var state = "play";

function refreshDatTime() {
    fetch('http://localhost:8080/api/dateTimeSimu')
        .then(resp => resp.json())
        .then(simEtat => {
            document.querySelector("#dateTimeSim").innerHTML = simEtat.time;
        })
}

function buttonPlayPress() {
    let fetch_content;
    if (state == 'pause') {
        state = 'play';
        document.querySelector("#button_play").innerHTML = `<img src="assets/img/play-circle-solid.svg" />`;
        fetch_content = true
    }

    else if (state == 'play') {
        state = 'pause';
        document.querySelector("#button_play").innerHTML = `<img src="assets/img/pause-circle-solid.svg" />`;
        fetch_content = false
    }

    fetch("http://localhost:8080/api/play-pause", {
        method: 'POST',

        headers: {
            'Content-Type': 'application/json'
        },

        body: fetch_content
    })
}

refreshDatTime();
buttonPlayPress();
window.setInterval(refreshDatTime, 6000);