# Asset Uploader Service

S3 asset uploader API for asset uploading

1. client gets pre signed url of s3 for uploading from uploader service
2. client uses pre signed url to upload any assets to s3 bucket directly
3. client notifies the uploader service that the asset has been completed
4. client gets the s3 download url from the uploader service

## Getting Started

In order to run this project, you will need to configure an Amazon S3 Bucket.

### Prerequisites

```
Maven: 3.6+
JDK: 1.8+
Aws S3: Amazon S3 Bucket is required

```
## Configuration & Start Service
 
1. In AWS, create an IAM user with the following policy in order to be able to access your bucket.

```json
{
    "Version": "version_date",
    "Statement": [
        {
            "Sid": "id",
            "Effect": "Allow",
            "Action": [
                "s3:GetObject",
                "s3:PutObject",
            ],
            "Resource": [
                "arn:aws:s3:::bucket_name"
            ]
        }
    ]
}
```

2. Update src/main/resources/application.properties. Please Note the properties file has to be valid in order to run the service.
```
s3.apiKey=<s3-api-key>
s3.apiSecret=<s3-api-secret>
s3.bucketName=<s3-bucket-name>
s3.region=<s3-region>
s3.urlExpirySeconds=60 #default upload or download url expiration seconds
```

3. Start Service

```
./mvnw spring-boot:run
```

## API Description

by default the base url of api service is http://localhost:8080

```
1. The service has an HTTP POST endpoint to upload a new asset.
POST /asset
Body: empty
Sample response
{
 “upload_url”: <s3-signed-url-for-upload:string>,
 “id”: <asset-id:integer>
}

2. The client should be able to make a POST call to the s3 signed URL to upload the asset
ex.
curl -v -X PUT '<upload_url>' -H 'Content-Type:application/octet-stream' --data-binary '<file-name>'

3. To mark the upload operation as complete, the service should provide a PUT endpoint as follows:
PUT /asset/<asset-id:integer>
Body:
{
 “Status”: “uploaded:string”
}

4. When a Get request is made on the asset, the service should return an s3 signed URL for download with the timeout in seconds as a URL parameter. If the timeout is not specified assume 60 seconds.
GET /asset/<asset-id:integer>?timeout=100
Sample response
{
 “Download_url”: <s3-signed-url-for-download:string>
}

```

Error Code
```
10: Number format error (Asset id should be an integer)
20: Illegal argument error (Asset doesn't exist)
30: upload failed error (Asset is not uploaded correctly)
```

Sample Error Response

```
{
    "statusCode": 10,
    "errorMessage": "Please input a number argument"
}
```


## Running the tests

Mockito is used to mock S3 service


## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Yadi Tang** - *Initial work* - (https://github.com/canior)

## License

This project is licensed under the MIT License

## Acknowledgments

* This is a demo asset uploader api for S3 service
