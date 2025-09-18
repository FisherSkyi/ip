# Seb: A Task Management Bot

### How to use the priority feature
1. When adding a task, you can specify its priority by "/priority \<level\>" at the end of your command. For example:
   - `todo add Buy groceries /priority 1`
   - `deadline add Submit report /by 2025-10-01 /priority 2`
   - `everyday add Exercise /from 2025-10-01 /to 2025-10-05 /priority 2`

2. set priority of existing task by "priority \<index\> \<level\>"

> [!NOTE]
> Priority levels:
> - 3: HIGH
> - 2: MEDIUM
> - 1: LOW
> - Any other value: UNSPECIFIED

> [!IMPORTANT]
> priority value must be 1, 2 or 3 to be valid, anything else will be set to "UNSPECIFIED" by default.

current bugs:
 - [ ] task add without priority causes error
 - [ ] 