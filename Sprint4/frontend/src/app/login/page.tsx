import CamposLogin from "@/components/CamposLogin";
import Link from "next/link";

export default function Login() {
  return (
    <main className="grow flex">
      <section className="w-2/3 m-auto">
        <h1 className="w-full text-center text-6xl text-blue-600">Login</h1>
        <CamposLogin />
      </section>
      <article className="w-1/3 bg-blue-400 flex flex-col items-center justify-center text-center p-3">
          <h1 className="text-7xl text-white m-5">Novo Aqui?</h1>
          <h3 className="text-4xl text-white m-5">Crie uma conta e aproveite todas as funcionalidade da nossa IA para dignosticar seu carro</h3>
          <Link href={"/cadastro/usuario"} className="mt-5 rounded-3xl bg-white w-2/5 p-3 text-center text-xl">Cadastrar</Link>    
      </article>
    </main>
  );
}