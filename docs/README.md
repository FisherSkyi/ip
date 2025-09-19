# Seb User Guide

![Ui image]: ./images/Ui.png

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

### adding deadlines
**Input Syntax:** `deadline <task_description> /by <due_date> [/priority <level>]`\
**Example:** `deadline Submit report /by 2025-10-01 /priority 2`\
**Expected Outcome:** Seb will add a DEADLINE task with the specified description, due date, and priority level.
```text
Got it. I've added this task:
    [D][ ] Submit report (by: Oct 1 2025) (priority: MEDIUM)
Now you have 2 tasks in the list.
```

> [!NOTE]
> Date format for DEADLINE and EVENT tasks can be in one of the following formats:
> - case1: `YYYY-MM-DD` (e.g., `2025-10-01`).
> - case2: `MM-DD-YYYY` (e.g., `10-01-2025`)
> - case3: `DD-MM-YYYY` (e.g., `01-10-2025`)\
> *Note that if both case2 and case3 are used, Seb will interpret the date as case2.*

### adding events
**Input Syntax:** `everyday <task_description> /from <start_date> /to <end_date> [/priority <level>]`\
**Example:** `everyday Exercise /from 2025-10-01 /to 2025-10-05 /priority 2`\
**Expected Outcome:** Seb will add an EVENT task with the specified description, start date, end date, and priority level.
```text
Got it. I've added this task:
    [E][ ] Exercise (from: Oct 1 2025 to: Oct 5 2025) (priority: MEDIUM)
Now you have 3 tasks in the list.
```

## managing tasks
### viewing tasks
**Input Syntax:** `list`\
**Expected Outcome:** Seb will display all tasks in the list with their details.
```text
Here are the tasks in your list:
1.[T][ ] Buy groceries (priority: LOW)
2.[D][ ] Submit report (by: Oct 1 2025) (priority: MEDIUM)
3.[E][ ] Exercise (from: Oct 1 2025 to: Oct 5 2025) (priority: MEDIUM)
```
### marking/unmarking tasks
**Input Syntax:** `mark <task_index>` or `unmark <task_index>`\
**Example:** `mark 2` or `unmark 2`\
**Expected Outcome:** Seb will mark or unmark the specified task as completed or not completed.
```text
Nice! I've marked this task as done:
    [D][X] Submit report (by: Oct 1 2025) (priority: MEDIUM)
```
or
```text
OK, I've marked this task as not done yet:
    [D][ ] Submit report (by: Oct 1 2025) (priority: MEDIUM)
```

### finding tasks
**Input Syntax:** `find <keyword>`\
**Example:** `find report`\
**Expected Outcome:** Seb will display all tasks that contain the specified keyword in their description.
```text
Here are the matching tasks in your list:
1.[D][ ] Submit report (by: Oct 1 2025) (priority: MEDIUM)
```
### setting task priority
**Input Syntax:** `priority <task_index> <level>`\
**Example:** `priority 1 3`\
**Expected Outcome:** Seb will update the priority level of the specified task.
```text
Noted. I've updated the priority of this task:
    [T][ ] Buy groceries (priority: HIGH)
```

### deleting tasks
**Input Syntax:** `delete <task_index>`  
**Example:** `delete 2`  
**Expected Outcome:** Seb will remove the specified task from your list.
```text
Noted. I've removed this task:
    [D][ ] Submit report (by: Oct 1 2025) (priority: MEDIUM)
Now you have 2 tasks in the list.
```

## error handling
Seb will notify you if your input is invalid or if a command cannot be processed.  
**Examples:**
- Invalid command:
    ```text
    OOPS!!! I'm sorry, but I don't know what that means :-((
    ```
- Invalid date format:
    ```text
    Invalid date format. Please use one of: YYYY-MM-DD | MM-DD-YYYY | DD-MM-YYYY
    ```
- Invalid task index:
    ```text
    OOPS!!! The task index you provided is out of range.
    ```

## supported commands summary
| Command Example                                                 | Description                                         |
|-----------------------------------------------------------------|-----------------------------------------------------|
| `todo Buy groceries /priority 1`                                | Add a TODO task with priority                       |
| `deadline Submit report /by 2025-10-01 /priority 2`             | Add a DEADLINE task with due date and priority      |
| `everyday Exercise /from 2025-10-01 /to 2025-10-05 /priority 2` | Add an EVENT task with start/end dates and priority |
| `list`                                                          | View all tasks                                      |
| `mark 2` / `unmark 2`                                           | Mark/unmark a task as done                          |
| `find report`                                                   | Find tasks containing a keyword                     |
| `priority 1 3`                                                  | Set priority of a task                              |
| `delete 2`                                                      | Delete a task                                       |
| `bye`                                                           | Exit Seb                                            |

## tips
- You can use the UI to interact with Seb or use the command line.
- Priority is optional for all tasks; if omitted, it defaults to UNSPECIFIED.
- Dates must follow one of the supported formats.
- Task indices start from 1 in the displayed list.
