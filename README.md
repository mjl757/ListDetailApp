# ListDetailApp
An app that pulls in data from a free api to display of items that are clickable for additional details.

The API I decided to use was a freely available [Open5e](https://www.open5e.com/) RESTful API found [here](https://api.open5e.com/).

Using this API I pull a list of Magic Items from Fifth Edition Dungeons and Dragons down to display to the user. When the user selects an item from the list they are taken to a view to see the details of that item, with the description formatted using Markdown.

Given more time on the project I would like to make some of the following improvements to the app:
- Add far more tests to get the test coverage up a respectable level.
- Add some images to help denote what type of item is being displayed.
- Paginate the list of items being pulled down to allow for smoother loading of item.
- Implement some filtering and sorting on the list to make finding items easier.
- Split the Data and Domain layers into their own modules, to more solidly seperate concerns.
- Add it's own unique launch icon.
- More thuroughly polish the app.
