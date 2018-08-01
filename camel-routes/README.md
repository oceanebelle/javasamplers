# Module that tests: 

1. Stream a large file using spring RestController
2. Create a camel route that extracts the large file after startup

## Testing

1. ContextLoadTest - test to ensure spring can wire all beans
2. AppTest - test the route while mocking out the http using AdviceWith