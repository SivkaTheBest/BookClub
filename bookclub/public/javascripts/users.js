function UserModel(model) {
    var self = this;
    self.login = model.login;
}

function UserStatisticViewModel() {
    var self = this;

    self.login = ko.observable("");
    self.total = ko.observable(0);
    self.positive = ko.observable(0);
    self.neutral = ko.observable(0);
    self.negative = ko.observable(0);
    self.average = ko.observable(0);
    self.isYou = ko.observable(false);
    self.showUserInfo = ko.observable(false);

    self.getInfo = function(login) {
        $.ajax({
            type: 'GET',
            url: '/user',
            dataType: 'json',
            data: {
                login: login
            },
            success: function (userInfoModel) {
                self.login(userInfoModel.login);
                self.total(userInfoModel.total);
                self.positive(userInfoModel.positive);
                self.neutral(userInfoModel.neutral);
                self.negative(userInfoModel.negative);
                self.average(userInfoModel.average);
                self.isYou(userInfoModel.isYou);
                self.showUserInfo(true);
            }
        });
    }
}

function UserInfoViewModel() {
    var self = this;

    self.userStatistic = ko.observable(new UserStatisticViewModel());
    self.users = ko.observableArray([]);

    $.ajax({
        type: 'GET',
        url: '/users',
        dataType: 'json',
        success: function (data) {
            $.each(data, function(index, element) {
                self.users.push(new UserModel(element));
            });
        }
    });
}