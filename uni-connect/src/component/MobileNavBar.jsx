import { AiOutlineHome, AiFillHome  } from "react-icons/ai";
import { MdOutlineExplore, MdExplore, MdOutlineBookmarkBorder, MdOutlineBookmark } from "react-icons/md";
import { FaRegUser, FaUser } from "react-icons/fa";
import { GrEdit } from "react-icons/gr";
import { NavLink } from "react-router-dom";
import { AuthContext } from "../context/AuthContext";
import { useContext } from "react";

function MobileNavBar() {
    const { userId } = useContext(AuthContext);
    return (
        <nav className="block sm:hidden">

            <div className="fixed bottom-20 right-5 w-100 h-100 p-3 text-2xl rounded-full block xl:hidden cursor-pointer
                bg-blue-600 hover:bg-blue-800 z-10">
                <GrEdit
                    onClick={() => {} }>
                </GrEdit>
            </div>

            <ul className="fixed bottom-0 w-full sm:pl-2 flex justify-around bg-slate-300 z-10">
                <li >
                    <NavLink to="/home" className="flex py-4 gap-3 px-3 cursor-pointer hover:bg-slate-200 rounded-[15rem] active:bg-slate-300">
                        {({ isActive }) =>
                            isActive ? (
                                <>
                                    <AiFillHome className="text-[1.6rem] font-bold" />
                                </>
                            ) : (
                                <>
                                    <AiOutlineHome className="text-[1.6rem]" />
                                </>
                            )}
                    </NavLink>
                </li>
                <li>
                    <NavLink to="/resources" className="flex py-4 gap-3 px-3 cursor-pointer hover:bg-slate-200 rounded-[15rem] active:bg-slate-100">
                        {({ isActive }) => 
                            isActive ? (
                                <>
                                    <MdExplore className="text-[1.6rem] font-bold"/> 
                                </>
                            ) : (
                                <>
                                    <MdOutlineExplore className="text-[1.6rem]"/>
                                </>
                            )}
                    </NavLink>
                </li>
                <li>
                    <NavLink to="/events" className="flex py-4 gap-3 px-3 cursor-pointer hover:bg-slate-200 rounded-[15rem] active:bg-slate-100">
                        {({ isActive }) => 
                            isActive ? (
                                <>
                                    <MdExplore className="text-[1.6rem] font-bold"/> 
                                </>
                            ) : (
                                <>
                                    <MdOutlineExplore className="text-[1.6rem]"/>
                                </>
                            )}
                    </NavLink>
                </li>
                <li>
                    <NavLink to="/students" className="flex py-4 gap-3 px-3 cursor-pointer hover:bg-slate-200 rounded-[15rem] active:bg-slate-100">
                        {({ isActive }) => 
                            isActive ? (
                                <>
                                    <MdOutlineBookmark className="text-[1.6rem] font-bold"/> 
                                </>
                            ) : (
                                <>
                                    <MdOutlineBookmarkBorder className="text-[1.6rem]"/>
                                </>
                            )}
                    </NavLink>
                </li>
                <li>
                    <NavLink to="/surveys" className="flex py-4 gap-3 px-3 cursor-pointer hover:bg-slate-200 rounded-[15rem] active:bg-slate-100">
                        {({ isActive }) => 
                            isActive ? (
                                <>
                                    <MdOutlineBookmark className="text-[1.6rem] font-bold"/> 
                                </>
                            ) : (
                                <>
                                    <MdOutlineBookmarkBorder className="text-[1.6rem]"/>
                                </>
                            )}
                    </NavLink>
                </li>
                <li>
                    <NavLink to={`/users/${userId}/profile`} className="flex py-4 gap-3 px-3 cursor-pointer hover:bg-slate-200 rounded-[15rem] active:bg-slate-100">
                        {({ isActive }) => 
                            isActive ? (
                                <>
                                    <FaUser className="text-[1.6rem] font-bold"/> 
                                </>
                            ) : (
                                <>
                                    <FaRegUser className="text-[1.6rem]"/>
                                </>
                            )}
                    </NavLink>
                </li>
            </ul>
        </nav>
    );
}

export default MobileNavBar;