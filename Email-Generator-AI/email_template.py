import google.generativeai as genai
import os

genai.configure(api_key=os.environ["API_KEY"])

model = genai.GenerativeModel("gemini-1.5-flash")
email_created = False
while email_created == False:
    email_type = input("What type of email do you want to make? (or type 'stop' to end program\n").lower()
    if(email_type == "stop"):
        print("Ending the program")
        break

    with open("email.txt", "w") as email:
        response = model.generate_content(f"Write a {email_type} email to a professor.")
        email.write(response.text)
        email_created = True
print("email for your type has been created and saved to email.txt")
