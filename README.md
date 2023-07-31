# Ident Addon App

The Ident Addon App is an application that executes SQL queries for a given database, parses the results, and inserts them into a specified Google Sheets.

## Project Structure

The project root directory contains the following files and folders:

- `ident-{version}.jar`: The application itself.
- `ident_addon_starter.bat`: A starter script that runs the application on server startup.
- `ident.log` and archived `.gz` logs: Log files for the application.
- `*.sql`: SQL files to be executed.
- `tokens` folder: contains credentials data required to log into a Google account and perform cleanups/insertions.
- `filesToClean.txt`: contains information about the Google Sheets that need to be daily updated and cleaned up first.
- `listOfSql.txt`: contains a list of SQL queries to be executed. Each row refers to one of the `.sql` files in the root directory, and the data for each file will be appended to the end of the corresponding Google Sheet.

## Credentials Information

The credentials are automatically updated daily. The first time you log in on a new machine or location, you will need to look into the logs, copy the given link, and approve the application to access Google Sheets.

## `filesToClean.txt` Structure
`12345RKdp3-n2jBvvSu_vIUmmqRNRIDLIqWLEo###List1`
- The part before the separator (`###`) is the Google Sheet ID, which can be taken from the URL.
- The part after the separator is the Google Sheet page name. Please note that it should only contain English symbols and no spaces.

## `listOfSql.txt` Structure
`query.sql###12345RKdp3-n2jBvvSu_vIUmmqRNRIDLIqWLEo###List1`
- The first parameter is the name of the SQL query that needs to be executed.
- The second parameter is the Google Sheet ID.
- The third parameter is the Google Sheet page name. 
 
The structure is similar to `filesToClean.txt`, but with an additional first parameter indicating the name of the SQL query to be executed.

## Feature: SQL Placeholder
The SQL queries may contain a placeholder `DATE_PLACEHOLDER`, which will be replaced with the 'yesterday' date. This allows for daily addition of new data into the Google Sheets. 