# MovieRatingApp
FindTopOrders.java is for stadalone code to find top 2 orders 
The file is available in com.test.movieratingapp package same Project 


Built on StringBoot for auto configuration and faster development

Prerequisites Data.sql anded in resource folder
-----------------------------------------------------------------
pass the customer/{id}/rate/4.45?movie="movieName"
Post call for rating movie url
eg:
localhost:8080/api/rest/customer/1/rate/3.45?movie=Kite

output:
{
    "movieName": "Kite",
    "customer": [
        {
            "id": 14,
            "customerId": 1,
            "firstName": "Raj",
            "lastName": "Kumar",
            "rating": 3.45
        }
    ],
    "id": 13
}



Get call for get top Avg rated  Movie name it will return movie name 
localhost:8080/api/rest/maxratedmovie

output: Bhabhubali

Get Call for get top Avg rated customer and overall Avg rating
localhost:8080/api/rest/maxratedcustomer

output: 

{
    "id": 1,
    "firstName": "Raj",
    "lastName": "Kumar",
    "customerAvgRating": 3.7833333333333337,
    "avgRating": 3.021428571428572
}
