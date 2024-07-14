import { useContext, useState } from "react";
import InputField from "../../component/InputField";
import { createFormFieldObject, getFormField, getObjectFromFormFieldObject, processErrors, setFieldError } from "../../util/RequestFormUtil";
import { EMPTY_STRING_REGEX } from "../../constant/Regex";
import { apiLogin } from "../../action/ApiActions";
import { decodeToken } from "../../util/JwtDecodeUtil";
import { Link, useNavigate } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";
import { toast } from "react-toastify";

function Login() {
    const { logIn } = useContext(AuthContext);
    const navigate = useNavigate();
    let [requestForm, setRequestForm] = useState(initForm());
    const [showHide, setShowHide] = useState(false);
    const [isLoading, setIsLoading] = useState(false);

    function initForm() {
        return createFormFieldObject({ username: "", password: "" });
    }



    const handleChange = (e) => {
        const { name, value } = e.target;
        setRequestForm(prev => ({
            ...prev,
            [name]: getFormField(value),
        }));
    };


    const handleSubmit = async (e) => {
        e.preventDefault();
        if (validateRequestForm()) {
            const response = await apiLogin(getObjectFromFormFieldObject(requestForm));
            if (response.status) {
                sessionStorage.setItem("user", JSON.stringify(response.data));
                toast.success(response.message);
                setRequestForm(initForm());
                logIn(response.data);
                navigate('/');
            }
            else {
                if (response.errors) {
                    const formWithErrs = processErrors(response.errors, requestForm);
                    setRequestForm({ ...formWithErrs });
                }
                toast.error(response.message);
            }
        }

    };

    const validateRequestForm = () => {
        let validationSuccess = true;
        const reqForm = { ...requestForm };

        if (!EMPTY_STRING_REGEX.test(requestForm.username.value)) {
            reqForm.username = setFieldError(requestForm.username, "Username must not be empty");
            validationSuccess = false;
        }

        if (!EMPTY_STRING_REGEX.test(requestForm.password.value)) {
            reqForm.password = setFieldError(requestForm.password, "Password must not be empty");
            validationSuccess = false;
        }
        if (!validationSuccess) {
            setRequestForm(reqForm);
        }
        return validationSuccess;
    };

    return (
        <>
            <div className="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
                <div className="sm:mx-auto sm:w-full sm:max-w-sm">
                    <img className="mx-auto h-10 w-auto" src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600" alt="University Connect" />
                    <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Sign in to your account</h2>
                </div>

                <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                    <form className="space-y-6" onSubmit={handleSubmit}>
                        <div>
                            <label htmlFor="email" className="block text-sm font-medium leading-6 text-gray-900">Email address</label>
                            <div className="mt-2">
                                <InputField
                                    type="text"
                                    name="username"
                                    onChange={handleChange}
                                    placeholder='Username'
                                    label="Username"
                                    value={requestForm.username.value}
                                    className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                />
                                 {requestForm.username.error && <span>{requestForm.username.message}</span>}
                            </div>
                        </div>

                        <div>
                            <div className="flex items-center justify-between">
                                <label htmlFor="password" className="block text-sm font-medium leading-6 text-gray-900">Password</label>
                            </div>
                            <div className="mt-2">
                                <InputField
                                    type="password"
                                    name="password"
                                    onChange={handleChange}
                                    placeholder='Password'
                                    label="Password"
                                    value={requestForm.password.value}
                                    className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"

                                />
                                 {requestForm.password.error && <span>{requestForm.password.message}</span>}
                            </div>
                        </div>

                        <div>
                            <button type="submit" className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Sign in</button>
                        </div>
                    </form>

                    <p className="mt-10 text-center text-sm text-gray-500">
                        Dont have an account?
                        <Link className="font-semibold leading-6 text-indigo-600 hover:text-indigo-500" to="/sign-up"> Sign Up </Link>
                    </p>
                </div>
            </div>
   


        </>


    );
}

export default Login;