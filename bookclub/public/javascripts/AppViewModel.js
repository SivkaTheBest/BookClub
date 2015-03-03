function UserModel(model) {
    var self = this;
    self.login = model.login;
}

function BookModel(model) {
    var self = this;
    self.id = model.id;
    self.name = model.name;
    self.author = model.author;
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

    $.ajax({
        type: 'GET',
        url: '/books',
        dataType: 'json',
        success: function (data) {
            $.each(data, function(index, element) {
                self.books.push(new BookModel(element));
            });
        }
    });
}

var AppModel = new AppViewModel()

ko.applyBindings(AppModel);