# Atelier - Une api parmi tant d'autres
#### Maimiti Saint-Marc B3 dev. web & app

## Contexte
Dans le cadre de la fin du module de Spring, il est demandé de réaliser l'atelier "une api parmi tant d'autres".

## Attendus
Le projet doit :
- Être sécurisé avec Spring Security.
- Permettre à un utilisateur de créer un compte et de se connecter.
- Permettre de faire “rebondir” deux requêtes de votre API vers l’API distante de votre choix.
- Permettre de scraper une partie de l’API de votre choix.
- - En enregistrant ces éléments dans une base de données.
- - En ajoutant un CRUD basé sur l’entité résultante de ce scrapping.
- Des rôles différents pour :
- - Faire rebondir une requête.
- - Lancer le scrapping.
- - Utiliser le CRUD.
- Le projet doit être proprement découpé (Services, DTO, etc)
- La base de données devra être initialisée proprement (via Flyway ou Liquibase).
- L’ensemble des routes devra pouvoir être testé avec une collection d’appels API (Postman / Bruno).

## Mise en place du projet
1) Créer un fichier `src/main/resources/application.properties` à la racine du projet
2) Y copier le contenu du fichier `src/main/resources/application.properties.sample` et y renseigner:
- - `PORT`: le port de votre base de données
- - `DATABASE`: le nom de la base de données utilisée pour le projet
- - `USERNAME`: le username pour accéder à votre base de données
- - `PASSWORD`: le mot de passe pour accéder à la base de données
- - `JWT_SECRET`: la chaine de caractère utilisée pour vérifier et signer les tokens jwt. Vous pouver utiliser la ligne de commande `openssl rand -base64 100` si vous avez openssl, elle génèrera une chaine de 100 caractères aléatoires.
3) Charger le maven du projet
4) Exporter la collection bruno pour avoir les appels api
5) N'oubliez pas de créer un environnement pour pouvoir y renseigner `{{connectionPath}}` qui vaut `http://localhost:8085/api/v1`.

## Description du projet
Trois roles sont définis pour ce projet:
- ROLE_USER qui va effectuer toutes les actions de crud
- ROLE_REBOND qui va effectuer les rebonds de requête
- ROLE_SCRAP qui va scrapper l'api distante

L'api distante utilisée est `https://random-data-api.com/api/v2/`, nous utiliserons les routes `/users` et `/beers`. Retrouvez sa documentation [ici](https://random-data-api.com/documentation).

Il sera donc possible d'interroger cette api distante sans rien ajouter en db. Scrapper les données et les ajouter en db. Effectuer un crud complet sur les données déja enregistrées.

## Documentation de l'api
### Security
- Registration with role_user : `/register/user`
- Registration with role_rebond: `/register/rebond`
- Registration with role_scrap: `/register/scrapper`
- Login: `/login`
- Check current rights: `/test/security`

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
- Get all beers from database: `/user/beers`
- Get beer by id: `/user/beers/{id}`
- Get beers with filters: `/user/beers/filter` Is used with the following request params:
- - style: get beers by style
- - brand: get beers by brand
- - name: get beers by name
- - **Note**: You can't use all the params at the same time. It is either style, brand, name, brand AND name.
- Delete beer by id: `/user/beers/{id}`
- Patch beer by id: `/user/beers/{id}`, a body is required (cf. bruno collection)
- Post beer: `/user/beers/new`, a body is required (cf. bruno collection)


#### Clients
- Get all clients from database: `/user/clients`
- Get client by id: `/user/clients/{id}`
- Get clients with filters: `/user/clients/filter` Is used with the following request params:
- - gender: get clients by gender
- - date_of_birth: get clients born before the given date of birth
- - city: get clients by the city in their address
- - country: get clients by the country in their addresses
- - **Note**: You can't use all the params at the same time. One param et once.
- Delete client by id: `/user/clients/{id}`
- Patch client by id: `/user/clients/{id}`, a body is required (cf. bruno collection)
- Post client: `/user/clients/new`, a body is required (cf. bruno collection)

