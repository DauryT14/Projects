import google.generativeai as genai
import os

genai.configure(api_key=os.environ["API_KEY"])

model = genai.GenerativeModel("gemini-1.5-flash")

#function that takes in a job role and generates interview questions for that role
def generate_questions(job_role):
    response = model.generate_content(f"Generate 5 common interview questions for a {job_role} job role. Only include the questions and nothing more")
    return response.text

#function that generate sample answers to sample interview questions for a specific role
def generate_answers(questions):
    response = model.generate_content(f"Generate sample answers for these questions (only inlude the answers for each question line by line (numbered) and nothing more)\n: {questions}")
    return response.text


#Testing
user_input = input("Welcome, for what role do you want to generate interview questions for?\n").lower()
questions_text = generate_questions(user_input)
with open("interview_questions.txt", "w") as questions:
    questions.write(questions_text)

answers_text = generate_answers(questions_text)

with open("interview_answers.txt", "w") as answers:
    answers.write(answers_text)
print("Sample interview questions have been created and saved to interview_questions.txt")
print("Sample interview answers have been created and saved to interview_answers.txt")
