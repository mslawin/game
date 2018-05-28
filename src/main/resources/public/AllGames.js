var GameModel = {
    gamesResults: ko.observableArray([]),
    randomPlayerWins: ko.observable(0),
    rockPlayerWins: ko.observable(0),
    draws: ko.observable(0)
};

function getGamesResults() {
    $.getJSON("/game/total", function (data) {
        if (data !== undefined) {
            GameModel.gamesResults(data.gameResults);
            GameModel.randomPlayerWins(data.randomPlayerWins);
            GameModel.rockPlayerWins(data.rockPlayerWins);
            GameModel.draws(data.draws);
        }
    });
}

$(function () {
    $.ajaxSetup({
        contentType: "application/json; charset=utf-8"
    });
    ko.applyBindings(GameModel);

    getGamesResults();
});