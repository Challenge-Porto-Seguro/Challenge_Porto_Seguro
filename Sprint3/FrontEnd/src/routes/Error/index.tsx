import { ErrorStyle } from "../../styledConfig"
import { Link } from "react-router-dom"

export default function Error() {
    document.title = "Error"
    return(
        <ErrorStyle>
            <article>
                <h1>Error 404 - NOT FOUND</h1>
            </article>
            <h3>Para voltar á pagina principal <Link to="/">Clique Aqui</Link></h3>
        </ErrorStyle>
        
    )
}