// src/page/survey/SurveyDetail.jsx
import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { apiFetchSurveyQuestions } from '../../action/ApiActions'; // Adjust this import based on your API action setup
import { toast } from 'react-toastify';

const SurveyQuestions = () => {
  const { id } = useParams();
  const [questions, setQuestions] = useState([]);

  useEffect(() => {
    const fetchQuestions = async () => {
      const response = await apiFetchSurveyQuestions(id); // Adjust API call based on your implementation
      if (response.status) {
        setQuestions(response.data);
      } else {
        toast.error(response.message);
      }
    };

    fetchQuestions();
  }, [id]);

  const handleSubmit = () => {
    // Implement submit logic here
    console.log('Submit answers');
  };

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-3xl font-bold mb-6">Survey</h1>
      <div className="grid gap-4">
        {questions.map((question) => (
          <div key={question.id} className="mb-4">
            <h2 className="text-xl font-semibold">{question.question}</h2>
            <textarea className="border border-gray-300 p-2 mt-2 w-full" placeholder="Enter your answer"></textarea>
          </div>
        ))}
        <button onClick={handleSubmit} className="bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded">
          Submit Survey
        </button>
      </div>
    </div>
  );
};

export default SurveyQuestions;
