import { Link } from "react-router-dom";

function ResourceFile(props) {
    return (
        <>
            <div className="my-2 text-blue-500 font-bold"
            onClick={()=>{props.downloadFile(props.file)}}
            >
              {props.file}
            </div>
  
          </>
      );
}

export default ResourceFile;