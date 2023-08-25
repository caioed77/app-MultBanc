import NavBar from '@/components/NavBar'

export default function Home() {
  return (
    <div className="max-w-full max-h-full">
      <NavBar />
      <div className="grid grid-flow-col justify-end mt-36">
        <div className="bg-black m-24 p-40 flex flex-co rounded-xl">
          <a
            href="p"
            className="group w-56 text-white rounded-lg border border-white inline-flex justify-end px-5 py-4 transition-colors hover:transparent hover:transparent hover:dark:transparent hover:dark:bg-transparent"
            target="_blank"
            rel="noopener noreferrer"
          >
            <h2 className={`mb-3 text-2xl font-semibold`}>
              Navegar{' '}
              <span className="inline-block transition-transform group-hover:translate-x-1 motion-reduce:transform-none">
                -&gt;
              </span>
            </h2>
          </a>
        </div>
      </div>
    </div>
  )
}