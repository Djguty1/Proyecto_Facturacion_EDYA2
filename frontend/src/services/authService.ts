import axios from "axios";

const API = "http://localhost:8080";

export const login = async (
    usuario: string,
    password: string
) => {

    const response = await axios.post(
        `${API}/auth/login`,
        {
            usuario,
            password,
        }
    );

    return response.data;
};