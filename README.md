# weather-app

Implemented a service with a REST API to get and add the current weather. The service uses the OpenWeatherMap API to get the weather data.
## Appendix
First, you need to create a weather database in your postgres.
Admin login : admin , password: admin
## Demo

Swagger ui after running

http://localhost:8888/ui

## Screenshots

![App Screenshot](https://i.imgur.com/zgppShc.png)

![App Screenshot2](https://i.imgur.com/ZgiT5of.png)

## API Reference

## Open for all users

```http
  GET /api/v1/weather/city/{cityName}
```

#### Register

```http
  POST /api/v1/auth/application/sign-up
```

| Parameter          | Type     | Description |
|:-------------------|:---------|:------------|
| `firstname`        | `string` |             |
| `lastname`         | `string` |             |
| `username`         | `string` | **unique**  |
| `password`         | `string` |             |
| `confrim password` | `string` |             |
 You can get a token from the response body

#### Login

```http
  POST /api/v1/auth/application/sign-in
```
| Parameter  | Type     | Description  |
|:-----------|:---------|:-------------|
| `username` | `string` | **Required** |
| `password` | `string` | **Required** |
    You can get a token from the response body

## Open for registered users

#### GET user own info

```http
  GET /api/v1/user/application/me
```

#### PUT user edit own info

```http
  PUT /api/v1/user/edit-self
```
| Parameter          | Type      | Description                     |
|:-------------------|:----------|:--------------------------------|
| `id`               | `Integer` |                                 |
| `firstname`        | `string`  |                                 |
| `lastname`         | `string`  |                                 |
| `current password` | `string`  | **must equal user db password** |
**Required JWT token**

#### POST RESET PASSWORD

```http
  POST /api/v1/user/reset-password
```

| Parameter          | Type      | Description                     |
|:-------------------|:----------|:--------------------------------|
| `new password`     | `string`  |                                 |
| `confrim password` | `string`  | **must equal new password**     |
| `current password` | `string`  | **must equal user db password** |
**Required JWT token**


#### GET get-all available cities

```http
  GET /api/v1/user/get-all-cities
```
**Required JWT token**
 Users can see only available cities

#### POST subscribe to city

```http
  POST /api/v1/user/subscribe-city-weather/{cityId}
```
| Parameter | Type      | Description            |
|:----------|:----------|:-----------------------|
| `cityId`  | `Integer` | **Required JWT token** |

#### DELETE unsubscribe from city

```http
  DELETE /api/v1/user/remove-subscribtion/{cityId}
```
| Parameter | Type      | Description            |
|:----------|:----------|:-----------------------|
| `cityId`  | `Integer` | **Required JWT token** |

#### GET get-all subscribed cities weather

```http
  GET /api/v1/user/get-subscribed-cities-weather
```
**Required JWT token**

## Open for admin users

#### GET get-all users

```http
  GET /api/v1/admin/user-list
```
**Required JWT token**

#### GET user details

```http
    GET /api/v1/admin/user-details/{userId}
```
| Parameter | Type      | Description            |
|:----------|:----------|:-----------------------|
| `userId`  | `Integer` | **Required JWT token** |

#### PATCH user edit

```http
    PATCH /api/v1/admin/edit-user
```
| Parameter               | Type      | Description    |
|:------------------------|:----------|:---------------|
| `id`                    | `Integer` | **Required**   |
| `enabled`               | `boolean` |                |
| `role`                  | `string`  | **ADMIN/USER** |
| `accountNonExpired`     | `boolean` |                |
| `accountNonLocked`      | `boolean` |                |
| `credentialsNonExpired` | `boolean` |                |
**Required JWT token**

#### POST Create city
    
```http
        POST /api/v1/city/create-city
```
| Parameter  | Type      | Description      |
|:-----------|:----------|:-----------------|
| `cityName` | `string`  | **Required**     |
| `enabled`  | `boolean` | **default true** |
**Required JWT token**

#### GET get-all cities

```http
  GET /api/v1/city/get-all-city
```
**Required JWT token**
#### GET get-all cities with disabled

```http
  GET /api/v1/city/get-all-city-with-disabled
```
**Required JWT token**

#### PATCH city edit

```http
    PATCH /api/v1/city/update-city/{cityId}
```
| Parameter  | Type      | Description  |
|:-----------|:----------|:-------------|
| `cityId`   | `Integer` | **Required** |
| `cityName` | `string`  |              |
| `enabled`  | `boolean` |              |

**Required JWT token**

#### GET city details

```http
    GET /api/v1/city/get-city-by-id/{cityId}
```
| Parameter  | Type      | Description  |
|:-----------|:----------|:-------------|
| `cityId`   | `Integer` | **Required** |

**Required JWT token**

#### PATCH update city weather

```http
    PATCH /api/v1/city/update-city-weather/{cityId}
```
| Parameter  | Type      | Description  |
|:-----------|:----------|:-------------|
| `cityId`   | `Integer` | **Required** |
#### auto update city weather from open weather api Link: https://api.weatherapi.com
 with Reactive Feign Client
**Required JWT token**

#### PATCH update all city's weather

```http
    api/v1/city/update-city-weather-all
```
 auto update all cities' weather from open weather api Link: https://api.weatherapi.com
 with Reactive Feign Client
####
**Required JWT token**

#### update city weather manually

```http api/v1/city/update-city-weather-manual/{cityId}
```
| Parameter  | Type      | Description  |
|:-----------|:----------|:-------------|
| `cityId`   | `Integer` | **Required** |
| `temp_c`   | `double`  |              |
| `temp_f`   | `double`  |              |
| `wind_kph` | `double`  |              |
| `wind_mph` | `double`  |              |
| `wind_dir` | `string`  |              |
| `humidity` | `double`  |              |
**Required JWT token**

## Features

- Spring security with JWT
- Swagger UI
- Postgres
- Docker
- Spring reactive
- Spring webflux
- Spring data r2dbc
- Spring boot
- Liquibase


## Tech Stack


**Server:** jdk11, spring boot, web flux, rective spring, reactive feign , spring security, jwt-token,liquibase, postgreSql, junit, mockito, java doc, docker, swagger ui, open weather api


## Feedback

If you have any feedback, please reach out to me at manguberdikom@gmail.com or https://t.me/Garbage_Collector_java


## Authors

- [@figocom](https://www.github.com/figocom)
