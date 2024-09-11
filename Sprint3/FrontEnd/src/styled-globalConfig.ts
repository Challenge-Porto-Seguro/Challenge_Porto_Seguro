import { createGlobalStyle } from "styled-components";
import "./App.css"

const GlobalConfig = createGlobalStyle`
    * {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
    }

    #root{
        font-family: var(--fonte-principal);
        min-height: 100vh;
        display: flex;
        flex-direction: column;
    }
`

export default GlobalConfig