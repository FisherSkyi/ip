# Seb: A Task Management Bot

Declare AI usage: 

| Extent of use                        | Tool                     |  
|--------------------------------------|--------------------------|
| Generate All JavaDoc by Autocomplete | Copilot<br/>(Gemini 2.5) |
| Close GUI when User Input "bye"      | ChatGPT-5                |

### How to use the priority feature
1. When adding a task, you can specify its priority by "/priority \<level\>" at the end of your command. For example:
   - `todo add Buy groceries /priority 1`
   - `deadline add Submit report /by 2025-10-01 /priority 2`
   - `everyday add Exercise /from 2025-10-01 /to 2025-10-05 /priority 2`
2. set priority of existing task by "priority \<index\> \<level\>"


> [!IMPORTANT]
> priority value must be positive integer.