import { Link } from "react-router-dom";
import ResourceFile from "./ResourceFile";
import { apiDownloadResource } from "../../action/ApiActions";

function Resource(props) {
    function downloadFile(file){
        const response=apiDownloadResource({filename:file},props.resource.id);
    }

    return (
        <div className="bg-white p-4 rounded-lg shadow-md mb-4">
            <h3 className="text-lg font-semibold mb-2">{props.resource.title}</h3>
            <p className="text-gray-600 mb-4">{props.resource.description}</p>

            {
                !props.resource.files ? (
                    <Link to={`/resources/${props.resource.id}`} className="text-blue-500 hover:underline">
                        View
                    </Link>
                ) : (
                    <></>
                )
            }
            {
                props.resource.files ? (
                    <>
                    {
                            props.resource.files.map((file)=>{
                                return (
                                    <ResourceFile downloadFile={downloadFile} key={file} file={file} url={props.resource.url} />
                                )
                            })
                        }
                    </>
                ) : (
                    <></>
                )
            }


        </div>
    );
}

export default Resource;