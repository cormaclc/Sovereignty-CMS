# Sovereignty-CMS 

## front-end link
http://sovereignty-frontend.s3-website-us-east-1.amazonaws.com/

## Database Schema 
https://docs.google.com/document/d/14BB-o9ghiKei-ucE61wVI9Mf3Y5gW016ZeuCZOwxEXs/edit?usp=sharing

## Github Link
https://github.com/cormaccollier/Sovereignty-CMS.git

## Swagger API

```
---
swagger: "2.0"
info:
  version: "1.0.0"
  title: "Sovereignty - CMS"
host: "ezsx1v4va5.execute-api.us-east-1.amazonaws.com"
basePath: "/task5"
schemes:
- "https"
paths:
  /allCards:
    get:
      operationId: "getCards"
      produces:
      - "application/json"
      responses:
        200:
          description: "200 response"
          schema:
            $ref: "#/definitions/CardList"
          headers:
            Access-Control-Allow-Origin:
              type: "string"
        400:
          description: "400 response"
        404:
          description: "404 response"
    options:
      consumes:
      - "application/json"
      produces:
      - "application/json"
      responses: {}
  /allImages:
    get:
      produces:
      - "application/json"
      responses:
        200:
          description: "200 response"
          schema:
            $ref: "#/definitions/ImageList"
          headers:
            Access-Control-Allow-Origin:
              type: "string"
        400:
          description: "400 response"
        404:
          description: "404 response"
    options:
      consumes:
      - "application/json"
      produces:
      - "application/json"
      responses: {}
  /createCard:
    post:
      operationId: "createCard"
      consumes:
      - "application/json"
      produces:
      - "application/json"
      parameters:
      - in: "body"
        name: "Card"
        required: true
        schema:
          $ref: "#/definitions/Card"
      responses:
        200:
          description: "200 response"
          schema:
            $ref: "#/definitions/Card"
          headers:
            Access-Control-Allow-Origin:
              type: "string"
        400:
          description: "400 response"
        409:
          description: "409 response"
    options:
      consumes:
      - "application/json"
      produces:
      - "application/json"
      responses: {}
  /deleteCard/{cardId}:
    post:
      consumes:
      - "application/json"
      produces:
      - "application/json"
      parameters:
      - name: "cardId"
        in: "path"
        required: true
        type: "string"
      responses:
        200:
          description: "200 response"
          schema:
            $ref: "#/definitions/Card"
          headers:
            Access-Control-Allow-Origin:
              type: "string"
        400:
          description: "400 response"
        404:
          description: "404 response"
    options:
      consumes:
      - "application/json"
      produces:
      - "application/json"
      responses: {}
  /duplicateCard:
    post:
      consumes:
      - "application/json"
      produces:
      - "application/json"
      parameters:
      - in: "body"
        name: "Card"
        required: true
        schema:
          $ref: "#/definitions/Card"
      responses:
        200:
          description: "200 response"
          schema:
            $ref: "#/definitions/Card"
          headers:
            Access-Control-Allow-Origin:
              type: "string"
        400:
          description: "400 response"
        404:
          description: "404 response"
    options:
      consumes:
      - "application/json"
      produces:
      - "application/json"
      responses: {}
  /getCard/{cardid}:
    get:
      consumes:
      - "application/json"
      produces:
      - "application/json"
      parameters:
      - name: "cardid"
        in: "path"
        required: true
        type: "string"
      responses:
        200:
          description: "200 response"
          schema:
            $ref: "#/definitions/Card"
          headers:
            Access-Control-Allow-Origin:
              type: "string"
        400:
          description: "400 response"
        404:
          description: "404 response"
    options:
      consumes:
      - "application/json"
      produces:
      - "application/json"
      responses: {}
  /updateCard:
    post:
      consumes:
      - "application/json"
      produces:
      - "application/json"
      parameters:
      - in: "body"
        name: "Card"
        required: true
        schema:
          $ref: "#/definitions/Card"
      responses:
        200:
          description: "200 response"
          schema:
            $ref: "#/definitions/Card"
          headers:
            Access-Control-Allow-Origin:
              type: "string"
        400:
          description: "400 response"
        404:
          description: "404 response"
    options:
      consumes:
      - "application/json"
      produces:
      - "application/json"
      responses: {}
  /uploadImage:
    post:
      consumes:
      - "application/json"
      produces:
      - "application/json"
      parameters:
      - in: "body"
        name: "Image"
        required: true
        schema:
          $ref: "#/definitions/Image"
      responses:
        200:
          description: "200 response"
          schema:
            $ref: "#/definitions/ImageUrl"
          headers:
            Access-Control-Allow-Origin:
              type: "string"
    options:
      consumes:
      - "application/json"
      produces:
      - "application/json"
      responses: {}
definitions:
  CardList:
    type: "object"
    properties:
      cards:
        type: "array"
        items:
          $ref: "#/definitions/Card"
  ImageUrl:
    type: "object"
    properties:
      url:
        type: "string"
        description: "url for image in s3 bucket"
  Page:
    type: "object"
    properties:
      uuid:
        type: "string"
        description: "unique id for a page"
      elements:
        type: "array"
        items:
          $ref: "#/definitions/VisualElement"
      isModifiable:
        type: "boolean"
        description: "whether or not the page is modifiable (back page or not)"
        default: false
  VisualElement:
    type: "object"
    properties:
      uuid:
        type: "string"
        description: "unique id for a element"
      updated:
        type: "integer"
        format: "int64"
        description: "0 is unchanged, 1 is updated or new, and 2 is deleted"
      eltType:
        type: "string"
        description: "either TEXT or IMAGE"
        enum:
        - "text"
        - "image"
      xPos:
        type: "integer"
        format: "int64"
      yPos:
        type: "integer"
        format: "int64"
      height:
        type: "integer"
        format: "int64"
      width:
        type: "integer"
        format: "int64"
      text:
        type: "string"
        description: "text of visual element (text element)"
      font:
        type: "string"
        description: "font of visual element (text element)"
      imageUrl:
        type: "string"
        description: "image s3 url (image element)"
  Image:
    type: "object"
    properties:
      imageName:
        type: "string"
        description: "name of image (so that it can be retrieved and used in multiple\
          \ cards)"
      image:
        type: "string"
        description: "base64 encoded image"
  Card:
    type: "object"
    required:
    - "eventType"
    - "orientation"
    - "recipient"
    properties:
      uuid:
        type: "string"
        description: "unique id for every card"
      eventType:
        type: "string"
        description: "The type of event the card is for"
      recipient:
        type: "string"
      orientation:
        type: "string"
        description: "either Horizontal or Vertical"
        enum:
        - "horizontal"
        - "vertical"
      frontPage:
        $ref: "#/definitions/Page"
      leftPage:
        $ref: "#/definitions/Page"
      rightPage:
        $ref: "#/definitions/Page"
      backPage:
        $ref: "#/definitions/Page"
  ImageList:
    type: "object"
    properties:
      images:
        type: "array"
        items:
          $ref: "#/definitions/ImageUrl"
```
