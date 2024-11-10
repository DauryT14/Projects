choices = ["sick", "running late", "vacation"]
sick_message = (
    "Good morning Professor, \n\n"
    "I am feeling unwell today and don't really have the energy to come to class today. "
    "I will get the today's class notes from someone in class, so I don't fall behind. "
    "I just wanted to let you know in case you were wondering why I am absent in class today.\n\n"
    "Please let me know if you have any questions or anything that I should know from today's class. "
    "Thank you and I hope you have a great rest of your day!\n\n\n\n\n"
    "Gladly,\nDaury Toribio"
)

running_late_message = (
    "Good morning Professor, \n\n"
    "I hope you are having a good morning so far. I just to let you know that I will be running late because [enter reason here].\n\n"
    "Please let me know if you have any questions or concerns and I will see you in class!\n\n\n\n"
    "Gladly,\nDaury Toribio"
)

vacation_message = (
    "Good morning Professor, \n\n"
    "I just wanted to let you know that I will be traveling on vacation to [enter country/city here] and will miss the next [enter # weeks]. "
    "I will try to keep up with everything that I might miss when I have the chance, but please don't hesitate to email me about anything "
    "that I might need to know during my time away.\n\n"
    "Thank you and I hope you have a great rest of your day!\n\n\n\n"
    "Glady,\nDaury Toribio"
)

email_created = False
while email_created == False:
    message_choice = input("What type of email do you want to send? (sick, running late, or vacation) (or type 'stop' to end program)\n").lower()
    if(message_choice == "stop"):
        print("Ending the program")
        break
    elif(message_choice not in choices):
        print("Please choose an email type from the choices above")
    
    with open("email.txt", "w") as email:
        if(message_choice == "sick"):
            email.write(sick_message)
            email_created = True
        elif(message_choice == "running late"):
            email.write(running_late_message)
            email_created = True
        elif(message_choice == "vacation"):
            email.write(vacation_message)
            email_created = True
    if(message_choice in choices):
        print(f"The email for '{message_choice}' has been written to 'email.txt'.")
