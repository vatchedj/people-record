# people-record

This app takes in records about people and returns them, sorted in different ways. 

There are two ways to use this app: from the command line and the API. These are discussed below.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

### Command Line

    lein run /path/to/file.txt [/path/to/another/file.txt ...]

### API

To start a web server for the application, run:

    lein ring server

## Usage

### Command Line

Pretty self-explanatory. Run the command with one or more input files and get the output.

### API

The API runs on localhost:3000

#### POST /records

Post a JSON paylod like the one below:

    {
        "line" : "Hicks | Xavier | Male | Persian-blue | 6/30/2011"
    }

#### GET /records/gender

Returns the records sorted by gender (females first), then) by last name, ascending.

#### GET /records/birthdate

Returns the records sorted by birthdate, ascending.

#### GET /records/name

Returns the records sorted by last name, descending.

## Code Coverage

    lein cloverage

## License

Copyright © 2021 Vatche Jabagchourian
