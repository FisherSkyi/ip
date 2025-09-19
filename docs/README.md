# Seb User Guide

[Ui image]: ./images/Ui.png

Seb is a task management bot that helps you keep track of your tasks efficiently.

It supports 3 types of tasks: TODO, DEADLINE and EVENT.\
You can add, delete, mark/unmark tasks, view task list, find tasks by keywords and manage priorities of tasks.

## Table of Contents
- [Getting Started](#getting-started)
- [Adding Tasks](#adding-tasks)
  - [Adding TODOs](#adding-todos)
  - [Adding DEADLINEs](#adding-deadlines)
  - [Adding EVENTs](#adding-events)
- [Managing Tasks](#managing-tasks)
  - [Viewing Tasks](#viewing-tasks)
  - [Marking/Unmarking Tasks](#markingunmarking-tasks)
  - [Finding Tasks](#finding-tasks)
  - [Setting Task Priority](#setting-task-priority)
- [Delete Tasks](#adding-deadlines)

## getting started

1. Download the latest version of Seb from the [repository](https://github.com/FisherSkyi/ip/releases).
2. Ensure you have Java 17 or above installed on your machine.
    > [!NOTE]
    > If you are using MacOS, please follow [this guide](https://se-education.org/guides/tutorials/javaInstallationMac.html).
3. Run the application using the command: `java -jar seb.jar`.

### Say Hi to Seb
**Input Syntax:** `hi` or `hello` (independent of case).
**Expected Outcome:** Seb will greet you with a welcome message.
```text
Hello! I'm Seb.
What can I do for you?
```

### Bye Seb
**Input Syntax:** `bye` (independent of case).
**Expected Outcome:** Seb will bid you farewell and terminate the session.
```text
Bye. Hope to see you again soon!
```

## adding tasks

### adding todos
**Input Syntax:** `todo <task_description> [/priority <level>]`\
**Example:** `todo Buy groceries /priority 1`\
**Expected Outcome:** Seb will add a TODO task with the specified description and priority level.
```text
Got it. I've added this task:
    [T][ ] Buy groceries (priority: LOW)
Now you have 1 task in the list.
```
> [!NOTE]
> Priority levels:
> - 1: LOW
> - 2: MEDIUM
> - 3: HIGH
> - Any other value: UNSPECIFIED 
> If priority is not specified, it defaults to UNSPECIFIED.


