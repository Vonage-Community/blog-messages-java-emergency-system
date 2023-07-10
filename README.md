# WhatsApp-Based Emergency Response System Demo Code

* [Requirements](#requirements)
* [Installation and Usage](#installation-and-usage)
  * [API Credentials](#api-credentials)
  * [Running the Application](#running-the-application)
* [Contributing](#contributing)
* [License](#license)

## Requirements

This application requires that you have the following installed locally:

* IDE of your choice (We use  IntelliJ IDEA)
* The latest version of Java 
* Gradle

Additionally, to test the application, you must have a Vonage account. You can create a Vonage account for free or manage your Vonage account details at the [Vonage Dashboard](https://developer.vonage.com).

## Installation and Usage

You can run this application by first cloning this repository locally and opening in your IDE. 

Once you have downloaded a local copy, change into the directory of the application and you can now set up the API credentials for your Vonage account.

### API Credentials

Inside the source code, add your Vonage API key and API Secret to the string values. 

As always, make sure not to commit your sensitive API credential data to any public version control. 

### Running the Application

1. Execute `gradle run` in your terminal.
2. Visit `http://localhost:3000` in your browser.

## Contributing

We ❤️ contributions from everyone! If you see something that needs fixing, then please follow the [GitHub Flow](https://guides.github.com/introduction/flow/index.html) and we'll try to incorporate it.

## License

This project is under the [Apache 2.0 License](https://github.com/Vonage-Community/blog-2fa-java-sample/blob/main/LICENSE).