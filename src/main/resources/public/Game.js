var GameModel = {
    gamesResults: ko.observableArray([]),
    randomPlayerWins: ko.observable(0),
    rockPlayerWins: ko.observable(0),
    draws: ko.observable(0),

    play : function () {
        $.post("/user", function (data) {
            var tab = GameModel.gamesResults() || [];
            tab.push(data);
            GameModel.gamesResults(tab);
            if (data.winner === 'Draw') {
                GameModel.draws(GameModel.draws() + 1);
            } else if (data.winner === 'RockPlayer') {
                GameModel.rockPlayerWins(GameModel.rockPlayerWins() + 1);
            } else {
                GameModel.randomPlayerWins(GameModel.randomPlayerWins() + 1);
            }
        });
    },

    reset : function () {
        $.ajax({
            url: "/user",
            type: "DELETE",
            success: function () {
                GameModel.gamesResults([]);
                GameModel.randomPlayerWins(0);
                GameModel.rockPlayerWins(0);
                GameModel.draws(0);
            }
        });
    }
};

function getGamesResults() {
    $.getJSON("/user", function (data) {
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