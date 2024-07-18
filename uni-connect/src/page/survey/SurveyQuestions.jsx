import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import { apiFetchSurveyQuestions, submitSurveyAnswers } from '../../action/ApiActions';
import { toast } from 'react-toastify';
import MobileNavBar from '../../component/MobileNavBar';
import AsideLeft from '../../component/AsideLeft';
import { AiOutlineArrowUp, AiOutlineEdit, AiOutlineDelete, AiOutlinePlus } from 'react-icons/ai';
import QuestionPopup from './SurveyQuestionPopup';


const SurveyQuestions = () => {
  const { id } = useParams();
  const [questions, setQuestions] = useState([]);
  const [answers, setAnswers] = useState({});
  const [showQuestionPopup, setShowQuestionPopup] = useState(false);
  const [currentQuestion, setCurrentQuestion] = useState(null);

  useEffect(() => {
    const fetchQuestions = async () => {
      const response = await apiFetchSurveyQuestions(4); // Hardcoded survey ID for testing
      if (response.status) {
        setQuestions(response.data);
      } else {
        toast.error(response.message);
      }
    };

    fetchQuestions();
  }, [id]);

  const handleChange = (questionId, value) => {
    setAnswers((prev) => ({
      ...prev,
      [questionId]: value,
    }));
  };

  const handleSubmit = async () => {
    const formattedAnswers = questions.map((question) => ({
      surveyQuestionId: question.id,
      response: answers[question.id] || '',
      submissionDate: answers[question.submissionDate] || '2024-01-01', // Placeholder date
    }));

    try {
      const response = await submitSurveyAnswers(formattedAnswers);
      if (response.status) {
        toast.success("Survey submitted successfully!");
      } else {
        toast.error(response.message);
      }
    } catch (e) {
      toast.error("Failed to submit survey.");
    }
  };

  const handleEditQuestion = (question) => {
    setCurrentQuestion(question);
    setShowQuestionPopup(true);
  };

  const handleNewQuestion = () => {
    setCurrentQuestion(null); // Reset currentQuestion for new question creation
    setShowQuestionPopup(true);
  };

  const handleSaveQuestion = (newQuestion) => {
    // Implement logic to save or update question
    console.log("Saving question:", newQuestion);
    setShowQuestionPopup(false);
    // Perform API call or state update as needed
  };

  return (
    <div className="container mx-auto p-4">
      <MobileNavBar />

      <div className="flex justify-center px-5 sm:px-32 md:mt-4">
        <div className="flex h-screen w-screen">
          <AsideLeft />

          <main className="md:mx-4 w-full sm:basis-2/3">

            <div className="container mx-auto p-4">
              <div className="flex justify-between mb-4">
                <h1 className="text-3xl font-bold">Survey</h1>
                <button
                  onClick={handleNewQuestion}
                  className="flex items-center bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600"
                >
                  <AiOutlinePlus className="mr-2" /> New Question
                </button>
              </div>
              <div className="grid gap-4">
                {questions.map((question) => (
                  <div key={question.id} className="mb-4">
                    <div className="flex items-center mb-2">
                      <h2 className="text-xl font-semibold mr-2">{question.question}</h2>
                      <button
                        onClick={() => handleEditQuestion(question)}
                        className="text-blue-500 mr-2 hover:underline"
                      >
                        <AiOutlineEdit className="text-blue-500" />
                      </button>
                      <button className="text-red-500 hover:underline">
                        <AiOutlineDelete className="text-red-500" />
                      </button>
                    </div>
                    <textarea
                      className="border border-gray-300 p-2 mt-2 w-full"
                      placeholder="Enter your answer"
                      value={answers[question.id] || ''}
                      onChange={(e) => handleChange(question.id, e.target.value)}
                    />
                  </div>
                ))}
                <button
                  onClick={handleSubmit}
                  className="bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded"
                >
                  Submit Survey
                </button>
              </div>
            </div>

          </main>
          {/* <AsideRight onEnter={onEnter} /> */}
          <a href="#">
            <AiOutlineArrowUp className="hidden sm:block fixed bottom-0 right-20 bg-blue-300 text-slate-50 text-5xl p-3 rounded-full mb-2 mr-20 hover:bg-blue-500" />
          </a>
        </div>
      </div>

      <QuestionPopup
        show={showQuestionPopup}
        onClose={() => setShowQuestionPopup(false)}
        onSave={handleSaveQuestion}
        initialQuestion={currentQuestion}
      />

    </div>
  );
};

export default SurveyQuestions;
