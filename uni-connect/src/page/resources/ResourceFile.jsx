import { Link } from "react-router-dom";

function ResourceFile(props) {
    return (
        <>
            <Link className="my-2 text-blue-500 font-bold"
            onClick={()=>{props.downloadFile(props.file)}}
            >
              {props.file}
            </Link>
  
          </>
      );
}

export default ResourceFile;