import { useState } from "react";
import InputField from "../../component/InputField";
import { createFormFieldObject, getFormField, getObjectFromFormFieldObject, processErrors, setFieldError } from "../../util/RequestFormUtil";
import { EMAIL_REGEX, EMPTY_STRING_REGEX, PASSWORD_REGEX } from "../../constant/Regex";
import { apiSignUp } from "../../action/ApiActions";
import { Link } from "react-router-dom";
import { toast } from "react-toastify";

function SignUp() {


    let [requestForm, setRequestForm] = useState(initForm());

    function initForm() {
        return createFormFieldObject({ email: "", username: "", password: "", confirmPassword: "" })
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
            const response = await apiSignUp(getObjectFromFormFieldObject(requestForm));
            if (response.status) {
                toast.success(response.message);
                setRequestForm(initForm());
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

        if (!EMAIL_REGEX.test(requestForm.email.value)) {
            reqForm.email = setFieldError(requestForm.email, "Email format not valid");
            validationSuccess = false;
        }
        if (!EMPTY_STRING_REGEX.test(requestForm.username.value)) {
            reqForm.username = setFieldError(requestForm.username, "Username must not be empty");
            validationSuccess = false;
        }
        if (!PASSWORD_REGEX.test(requestForm.password.value)) {
            reqForm.password = setFieldError(requestForm.password, "Password must be 8-20 characters long and include at least one lowercase letter, one uppercase letter, one number, and one special character.");
            validationSuccess = false;
        }
        if (requestForm.password.value !== requestForm.confirmPassword.value) {
            reqForm.confirmPassword = setFieldError(requestForm.password, "Confirm Password must match password");
            validationSuccess = false;
        }
        if (!validationSuccess) {
            setRequestForm(reqForm);
        }
        return validationSuccess;
    };





    return (

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
                                type="email"
                                name="email"
                                onChange={handleChange}
                                placeholder='Email'
                                label="Email"
                                value={requestForm.email.value}
                                className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                            />
                            {requestForm.email.error && <span>{requestForm.email.message}</span>}
                        </div>
                    </div>
                    <div>
                        <label htmlFor="email" className="block text-sm font-medium leading-6 text-gray-900">Username</label>
                        <div className="mt-2">
                            <InputField
                                type="text"
                                name="username"
                                onChange={handleChange}
                                placeholder='UserName'
                                label="UserName"
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
                        <div className="flex items-center justify-between">
                            <label htmlFor="confirmPassword" className="block text-sm font-medium leading-6 text-gray-900">Confirm Password</label>
                        </div>
                        <div className="mt-2">
                            <InputField
                                type="password"
                                name="confirmPassword"
                                onChange={handleChange}
                                placeholder='Confirm Password'
                                label="Confirm Password"
                                value={requestForm.confirmPassword.value}
                                className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"

                            />
                            {requestForm.confirmPassword.error && <span>{requestForm.confirmPassword.message}</span>}
                        </div>
                    </div>

                    <div>
                        <button type="submit" className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Sign up</button>
                    </div>
                </form>

                <p className="mt-10 text-center text-sm text-gray-500">
                    Already have an account?
                    <Link className="font-semibold leading-6 text-indigo-600 hover:text-indigo-500" to="/login"> Login </Link>
                </p>
            </div>
        </div>



    );
}

export default SignUp;