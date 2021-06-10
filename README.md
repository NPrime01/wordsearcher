# Word Searcher
Query a database for any string starting with the user input.

![image](https://user-images.githubusercontent.com/47795760/121573056-69d48e00-c9ea-11eb-8c64-93d1ad6a9fd0.png)

## How To Run:
1. Go to releases and download the latest release
2. Extract the zip file wherever you'd like to run the app from
3. Open the command line and `cd` into the directory you extracted the release into
4. Run the program with the command `java -jar wordsearcher-1.0.jar`
5. When it finishes initializing, you can navigate to `localhost:8080` to use the UI or you can use the API

## How to Use the API:
While the app is running:
- Send a GET request to `localhost:8080/api/[Mode]?word=[Query]`
  - Replace [Mode] with your choice of Query mode:
    - `sw` for Starts With mode
    - `ew` for Ends With mode
    - `co` for contians mode
  - Replace [Query] with the word you would like to search for

The response will have the following structure:
```
{
  "word": "[Query]",
  "mode": "[Mode]",
  "results": [ "list", "of", "words"]
}
```

## How to Use the UI:
From the main page, simply click the link to be taken to the interface.

When you reach the main interface, type your query into the textbox labeled "Your Query"

There are three query modes you can choose from:
- Starts With: query all strings from the database that start with your query
- Ends With: query all strings that end with your query
- Contains: query all strings that contain your input at any location

When Ready, simply click the Submit Query button and the program will return your query.

You can freely view the resuls of your query in the table at the bottom of the page. 

If need be, you can also copy the contents of the query to your clipboard by clicking the "Copy Query to Clipboard" button.
