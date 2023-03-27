class AppBase {
    // static DOMAIN_SERVER = 'http://localhost:3300';
    // static DOMAIN_SERVER = 'http://localhost:8080';
    static DOMAIN_SERVER = location.origin;

    static API_SERVER = this.DOMAIN_SERVER + '/api';

    static API_BOOK = this.API_SERVER + '/books';

    static API_AUTH = this.API_SERVER + '/auth';

    static API_CLOUDINARY = 'https://res.cloudinary.com/cloudinarymen/image/upload';

    static SCALE_IMAGE_W_80_H_80_Q_100 = 'c_limit,w_80,h_80,q_100';
    static SCALE_IMAGE_W_80_H_80_Q_85 = 'c_limit,w_80,h_80,q_85';
}

    class Book {
        constructor(id, name, author, price, quantity, rate) {
            this.id = id;
            this.name = name;
            this.author = author;
            this.price = price;
            this.quantity = quantity;
            this.rate = rate;
        }
    }

    class Rate {
        constructor(id, name) {
            this.id = id;
            this.name = name;
        }
    }

    class BookAvatar {
        constructor(id, fileName, fileFolder, fileUrl, fileType, cloudId) {
            this.id = id;
            this.fileName = fileName;
            this.fileFolder = fileFolder;
            this.fileUrl = fileUrl;
            this.fileType = fileType;
            this.cloudId = cloudId;
        }
    }

