# Atelier - Une api parmi tant d'autres
#### Maimiti Saint-Marc B3 dev. web & app


## Mise en place du projet
1) Créer un fichier `src/main/resources/application.properties` à la racine du projet
2) Y copier le contenu du fichier `src/main/resources/application.properties.sample` et y renseigner:
- - `PORT`: le port de votre base de données
- - `DATABASE`: le nom de la base de données utilisée pour le projet
- - `USERNAME`: le username pour accéder à votre base de données
- - `PASSWORD`: le mot de passe pour accéder à la base de données
- - `JWT_SECRET`: la chaine de caractère utilisée pour vérifier et signer les tokens jwt. Vous pouver utiliser la ligne de commande `openssl rand -base64 100` si vous avez openssl, elle génèrera une chaine de 100 caractères aléatoires.
3) Exporter la collection bruno pour avoir les appels api
4) N'oubliez pas de créer un environnement pour pouvoir y renseigner `{{connectionPath}}` qui vaut `http://localhost:8085/api/v1`.

## Documentation de l'api
### Security
- Inscription avec le rôle **USER** : `/register/user`
- Inscription avec le rôle **REBOND** : `/register/rebond`
- Inscription avec le rôle **SCRAP** : `/register/scrapper`
- Connexion : `/login`
- Vérifier les droits actuels : `/test/security`

### Rebond
#### Beers
- Faire rebondir une requête get beer sur l'api distante (fait uniquement un rebond, n'ajoute rien en db): `/rebond/beers`

#### Clients
- Faire rebondir une requête get client sur l'api distante (fait uniquement un rebond, n'ajoute rien en db): `/rebond/clients`


### Scrapping
#### Beers
- Scrapper l'api distante et ajouter n éléments beer dans la base de données: `/scrapper/beers?number=n`

#### Clients
- Scrapper l'api distante et ajouter n éléments client dans la base de données: `/scrapper/clients?number=n`


### User (CRUD)
#### Beers
- Récupérer toutes les beers de la base de données : `/user/beers`
- Récupérer une beer par ID : `/user/beers/{id}`
- Récupérer des beers avec des filtres : `/user/beers/filter` (utilisé avec les paramètres suivants) :
    - `style` : récupérer les beers par style
    - `brand` : récupérer les beers par marque
    - `name` : récupérer les beers par nom
    - **Remarque** : Vous ne pouvez **pas** utiliser tous les paramètres en même temps. Vous pouvez filtrer par **style**, **marque**, **nom**, ou par **marque ET nom** ensemble.
- Supprimer une beer par ID : `/user/beers/{id}`
- Mettre à jour une beer par ID (**PATCH**, nécessite un body, voir la collection Bruno) : `/user/beers/{id}`
- Ajouter une nouvelle beer (**POST**, nécessite un body, voir la collection Bruno) : `/user/beers/new`

#### Clients
- Récupérer tous les clients de la base de données : `/user/clients`
- Récupérer un client par ID : `/user/clients/{id}`
- Récupérer des clients avec des filtres : `/user/clients/filter` (utilisé avec les paramètres suivants) :
    - `gender` : récupérer les clients par genre
    - `date_of_birth` : récupérer les clients nés avant une date donnée
    - `city` : récupérer les clients selon la ville de leur adresse
    - `country` : récupérer les clients selon le pays de leur adresse
    - **Remarque** : Vous ne pouvez **pas** utiliser plusieurs paramètres en même temps. Un seul filtre est autorisé par requête.
- Supprimer un client par ID : `/user/clients/{id}`
- Mettre à jour un client par ID (**PATCH**, nécessite un body, voir la collection Bruno) : `/user/clients/{id}`
- Ajouter un nouveau client (**POST**, nécessite un body, voir la collection Bruno) : `/user/clients/new`  

