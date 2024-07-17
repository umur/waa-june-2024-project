function ResourceFormModal({ data, closeModal, handleChange, handleSave }) {
    return (
        <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center">
            <div className="bg-white p-6 rounded shadow-lg">
                <h2 className="text-xl font-bold mb-4">Edit Resource</h2>


                <>
                    <label className="block mb-2">Name</label>
                    <input
                        type="text"
                        name="title"
                        value={data.title}
                        onChange={handleChange}
                        className="block w-full mb-4 p-2 border border-gray-300 rounded"
                    />
                    <label className="block mb-2">Description</label>
                    <input
                        type="text"
                        name="description"
                        value={data.description}
                        onChange={handleChange}
                        className="block w-full mb-4 p-2 border border-gray-300 rounded"
                    />
                </>

                <div className="flex justify-end">
                    <button onClick={closeModal} className="mr-4 text-gray-700">Cancel</button>
                    <button onClick={handleSave} className="bg-blue-500 text-white px-4 py-2 rounded">Save</button>
                </div>
            </div>
        </div>
    );

}

export default ResourceFormModal;