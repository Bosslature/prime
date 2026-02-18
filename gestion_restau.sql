
CREATE DATABASE IF NOT EXISTS GestionRestaurant;
USE GestionRestaurant;


CREATE TABLE IF NOT EXISTS categorie (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS produit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prix_vente DOUBLE NOT NULL CHECK (prix_vente > 0),
    stock_actuel INT NOT NULL CHECK (stock_actuel >= 0),
    seuil_alerte INT NOT NULL CHECK (seuil_alerte >= 0),
    categorie_id INT NOT NULL,
    FOREIGN KEY (categorie_id) REFERENCES categorie(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS utilisateur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50) NOT NULL UNIQUE,
    mot_de_passe VARCHAR(255) NOT NULL,
    role ENUM('ADMIN','GERANT','UTILISATEUR') NOT NULL
);


CREATE TABLE IF NOT EXISTS mouvement_stock (
    id INT AUTO_INCREMENT PRIMARY KEY,
    produit_id INT NOT NULL,
    type ENUM('ENTREE','SORTIE') NOT NULL,
    quantite INT NOT NULL CHECK (quantite > 0),
    date_mouvement DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    motif VARCHAR(100),
    FOREIGN KEY (produit_id) REFERENCES produit(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS commande (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date_commande DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    etat ENUM('EN_COURS','VALIDE','ANNULE') NOT NULL,
    total DOUBLE NOT NULL DEFAULT 0,
    utilisateur_id INT NOT NULL,
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id) ON DELETE restrict
);


CREATE TABLE IF NOT EXISTS ligne_commande (
    id INT AUTO_INCREMENT PRIMARY KEY,
    commande_id INT NOT NULL,
    produit_id INT NOT NULL,
    quantite INT NOT NULL CHECK (quantite > 0),
    prix_unitaire DOUBLE NOT NULL CHECK (prix_unitaire > 0),
    montant DOUBLE GENERATED ALWAYS AS (quantite * prix_unitaire) STORED,
    FOREIGN KEY (commande_id) REFERENCES commande(id) ON DELETE CASCADE,
    FOREIGN KEY (produit_id) REFERENCES produit(id) ON DELETE CASCADE
);


INSERT INTO categorie(libelle) VALUES ('Boissons'),('Plats'),('Desserts');
INSERT INTO produit(nom, prix_vente, stock_actuel, seuil_alerte, categorie_id) 
VALUES ('Coca', 1.5, 50, 10, 1), ('Burger', 5, 20, 5, 2), ('Tiramisu', 3, 10, 2, 3);

INSERT INTO utilisateur(login, mot_de_passe, role) 
VALUES ('admin', 'admin123', 'ADMIN'), ('gerant', '123', 'GERANT'), ('user', '123', 'UTILISATEUR');
