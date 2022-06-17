# Github Actions
Platform to automate the developer workflows. One of the main benefit is CI CD tool instead of using the third party tools like Jenkins, etc. Setting the pipeline is easy. No need of devops to setup.

# Github 
Platform for open source projects or private projects. \

# Workflows
Chain of github actions 
Developers can host their project and they can make publicly available and can add contributers to contribute the project by creating the pull requests.\
Developers can do organizational tasks \
For example: Consider The project is dependent on library and the library made some updates. If those changes may affect the current project then the user can create new issue regardng the latest update of the library. Then the developer of the library needs to check the issue such as hoow major the issue is or is it reproducable. The PR will be created in the library and merged to the next release version of library. The users can update the library and can able to work.

the ci cd pipeline : merged code -> test -> build -> deploy.
The release notes has to be attached for the latest version or update the version

These workflows can be automated with the help of Github Actions

# Github Action Automate Workflows
Configure the automated actions \
Listen to `Github Events` [pushing the branch, creating the issue, creating the PR, merging PR] \
If any `Github Events` occur then these automated actions are executed in response \
workflows can be created depending on the event \
Github action examples: sorting the issue, providing label, assigning to the dev. \

Common workflow \
Commit -> Test -> Build -> push -> deploy


# Workflow Template
name - displays the name of workflow while executing \
on - Name of the github event that triggers the work flow \
jobs - It contains the sequence of actions(steps) that needs to be executed \
step - run commands/ actions/ setup tasks \
uses - selects as action and under action/... the reusable actions is hosted or existing action names can be written along with the version eg: Checkout@v2 \
      eg: uses : setup - action/setup-java@v1 with version 1.8 then the java is installed there \
run - run a command line command. eg: run : gradlew build


# secrets
Present in github to store passwords and username so we can use their reference in yaml file. eg ${{Secrets.username}}















