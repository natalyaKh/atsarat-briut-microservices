TESTING
1. add user

   -POST
    - http://localhost:8011/user-service/users/v1
      
    - request
   
        - {
          
          "mainEmail": "your_email",
          "notDecryptedPassword":"1111111111",
          "firstName": "natalya"
        }
          
    - response
   
        - {
          
          "content": {
          "uuidUser": "34ef2f7d-af55-430f-a403-2016dc4e3813",
          "mainEmail": "your_email",
          "firstName": "natalya",
          "secondName": null,
          "tz": null
          },
          "code": 201,
          "timestamp": "2021-04-29"
          
          }

2. confirm email by link
3. LOGIN user
    - POST
        - http://localhost:8088/users/login
    - request
      
        - {
          
          "mainEmail": "your_email",
          "password":"1111111111"
          
          }
    - response:
        - 200 OK
          got TOKEN

4. update-user - adding tz
    - PUT
        - 127.0.0.1:8011/user-service/users/v1/34ef2f7d-af55-430f-a403-2016dc4e3813 
          
    - request
        - {
          
          "firstName": "Vasya",
          "secondName":"VASILEV",
          "altEmail": "alt@al.v",
          "tz": "333333333"
          
          }
          
    - response
        - {
          
          "content": {
          "uuidUser": "34ef2f7d-af55-430f-a403-2016dc4e3813",
          "firstName": "Vasya",
          "secondName": "VASILEV",
          "tz": "333333333"
          },
          "code": 200,
          "timestamp": "2021-04-29"
          
          }

5. add child
   
   -POST
        - http://localhost:8011/children-service/child/v1/
    - request
        - {
          
          "firstName": "child",
          "secondName": "test",
          "tz":"111115118",
          "uuidParent":"34ef2f7d-af55-430f-a403-2016dc4e3813"
          }
   - response
     
        - {
          
          "content": {
          "uuidChild": "8c45a7a8-ac3e-495a-b117-2d880b88e577",
          "firstName": "child",
          "secondName": "test",
          "uuidParent": "34ef2f7d-af55-430f-a403-2016dc4e3813",
          "uuidRespPers": null,
          "tz": "111115118"
          },
          "code": 201,
          "timestamp": "2021-04-29"
          
          }

6. add tsofim details
    - POST
        - http://localhost:8011/tsofim-service/tsofiml/v1
            - request
                - {
                  
                  "uuidChild":"8c45a7a8-ac3e-495a-b117-2d880b88e577",
                  "place":"שורק",
                  "groupTs":"כפיר",
                  "childClass": "ד",
                  "school":"benGurion"
                  
                  }

7. pull atsarat-briut
    - GET
      - http://localhost:8086/tsofiml/v1/parse/8c45a7a8-ac3e-495a-b117-2d880b88e577

    - as a result - getting email with screen-shot of atsarat-briut
    
