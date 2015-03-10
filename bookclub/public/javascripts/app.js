function BookModel(model) {
    var self = this;
    self.id = model.id;
    self.name = model.name;
    self.author = model.author;
    self.userRating = ko.observable(model.userRating);
    self.rating = ko.observable(model.rating);

    self.showDetails = ko.observable(false);

    self.enableDetails = function() {
        self.showDetails(true);
    };

    self.disableDetails = function() {
        self.showDetails(false);
    }

    self.save = function() {
        $.ajax({
            type: 'GET',
            url: '/rate',
            dataType: 'json',
            data: {
                bookId: self.id,
                userRating: self.userRating()
            },
            success: function (bookModel) {
                self.userRating(bookModel.userRating);
                self.rating(bookModel.rating);
            }
        });
    }

    self.deleteRating = function() {
        $.ajax({
            type: 'GET',
            url: '/deleteRating',
            dataType: 'json',
            data: {
                bookId: self.id
            },
            success: function (bookModel) {
                self.userRating(bookModel.userRating);
                self.rating(bookModel.rating);
            }
        });
    }
}

ko.bindingHandlers.fadeVisible = {
    init: function(element, valueAccessor) {
        // Start visible/invisible according to initial value
        var shouldDisplay = valueAccessor();
        $(element).toggle(shouldDisplay);
    },
    update: function(element, valueAccessor) {
        // On update, fade in/out
        var shouldDisplay = valueAccessor();
        shouldDisplay ? $(element).fadeIn() : $(element).fadeOut();
    }
};

ko.bindingHandlers.starRating = {
    init: function(element, valueAccessor) {
        $(element).addClass("starRating");
        for (var i = 0; i < 5; i++) {
            $("<span>").appendTo(element);
        }

        // Handle mouse events on the stars
        $("span", element).each(function(index) {
            $(this).hover(
                function() {
                    $(this).prevAll().add(this).addClass("hoverChosen")
                },
                function() {
                    $(this).prevAll().add(this).removeClass("hoverChosen")
                }
            ).click(function() {
                    var observable = valueAccessor();  // Get the associated observable
                    observable(index + 1);               // Write the new rating to it
                });
        });
    },

    update: function(element, valueAccessor) {
        // Give the first x stars the "chosen" class, where x <= rating
        var observable = valueAccessor();
        $("span", element).each(function(index) {
            $(this).toggleClass("chosen", index < observable());
        });
    }
};

function BooksViewModel() {
    var self = this;

    self.books = ko.observableArray([]);

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

function BookList(model, books) {
    var self = this;

    self.startDate = model.startDate;
    self.endDate = model.endDate;
    self.books = ko.observableArray([]);

    $.each(model.bookIds, function(index, element) {
        self.books.push(books.getItem(element));
    });
}

function BookListViewModel() {
    var self = this;
    var books = new HashTable();
    self.bookLists = ko.observableArray([]);

    $.ajax({
        type: 'GET',
        url: '/lists',
        dataType: 'json',
        success: function (data) {
            $.each(data.books, function(index, element) {
                var book = new BookModel(element);
                books.setItem(book.id, new BookModel(element));
            });

            $.each(data.bookLists, function(index, element) {
                self.bookLists.push(new BookList(element, books));
            });
        }
    });
}

function AppViewModel() {
    var self = this;

    self.bookViewModel = new BooksViewModel();
    self.userInfoViewModel = new UserInfoViewModel();
    self.bookListViewModel = new BookListViewModel();
}

var AppModel = new AppViewModel()

ko.applyBindings(AppModel);