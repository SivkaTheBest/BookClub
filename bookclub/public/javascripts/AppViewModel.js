function UserModel(model) {
    var self = this;
    self.login = model.login;
}

function BookModel(name, author) {
    var self = this;
    self.name = name;
    self.author = author;
}

function AppViewModel() {
    var self = this;

    self.users = ko.observableArray([]);
    self.books = ko.observableArray([]);

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

var AppModel = new AppViewModel()

ko.applyBindings(AppModel);