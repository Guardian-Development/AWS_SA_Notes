def lambda_handler(event, context):
    print("Lambda executed")

    response = {
        "statusCode": 200,
        "headers": {
            "Access-Control-Allow-Origin": "*"
        },
        "body": "Hello from Lambda!"
    }

    return response