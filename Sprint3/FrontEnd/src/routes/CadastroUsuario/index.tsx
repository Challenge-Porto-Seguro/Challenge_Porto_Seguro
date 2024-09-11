import Formulario from "../../components/Formulario";
import { CadastroPageStyle, FormularioCadastroStyle } from "../../styledConfig";
import { CriacaoFormulario } from "../../types";

export default function CadastroUsuario() {


    const listaCampos: CriacaoFormulario[] = [
        {id: "nome", nomeCampo: "Nome", tipo: "text", placeHolder: "Digite seu nome"},
        {id: "email", nomeCampo: "Email", tipo: "email", placeHolder: "Digite seu email"},
        {id: "senha", nomeCampo: "Senha", tipo: "password", placeHolder: "Digite seu email"},
        {id: "confirma_senha", nomeCampo: "Confirmar Senha", tipo: "password", placeHolder: "confirme sua senha"},
        {id: "bairro", nomeCampo: "Bairro", tipo: "text", placeHolder: "Digite seu bairro"},
        {id: "cidade", nomeCampo: "Cidade", tipo: "text", placeHolder: "Digite sua cidade"},
        {id: "estado", nomeCampo: "Estado", tipo: "text", placeHolder: "Digite seu estado"}
    ]

    const listaEnderecoContainer: CriacaoFormulario[] = [
        {id: "rua", nomeCampo: "Rua", tipo: "text", placeHolder: "Digite o nome da sua rua"},
        {id: "numero", nomeCampo: "Numero", tipo: "number", placeHolder: ""},
    ]
    document.title = "Cadastro de Usuario"
    return (
        <CadastroPageStyle>

            <h1>Cadastro de Usuario</h1>
            <FormularioCadastroStyle>

                {
                    listaCampos.map((campo, index) => (
                        index - 1 === 3 ? 
                        <div className="endereco_container">
                        {
                            listaEnderecoContainer.map((end, index) => (
                                <div className={end.id}>
                                    <Formulario key={index} id={end.id} nomeCampo={end.nomeCampo} tipo={end.tipo} placeHolder={end.placeHolder} />
                                </div>
                            ))
                        }
                        </div> 
                         :  
                        <Formulario key={index} id={campo.id} nomeCampo={campo.nomeCampo} tipo={campo.tipo} placeHolder={campo.placeHolder} />
                    ))
                }

                <button className="botao_cadastro">Cadastrar</button>
                
            </FormularioCadastroStyle>

            
        </CadastroPageStyle>
    )
}