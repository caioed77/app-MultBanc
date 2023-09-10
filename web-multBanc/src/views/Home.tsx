import { Fragment, useEffect, useState } from 'react'
import { Disclosure, Menu, Transition } from '@headlessui/react'
import { Bars3Icon, BellIcon, XMarkIcon } from '@heroicons/react/24/outline'
import { useUser } from '../context/authContext'
import { Outlet, useNavigate } from 'react-router-dom'
import { TAccount } from '../@Types/Account'
import { api } from '../service/api'
import { SignOut } from '@phosphor-icons/react'

const navigation = [
  { name: 'Conta', href: 'minhaConta', current: true },
  { name: 'Saque', href: 'saqueDeposito', current: false },
  { name: 'Deposito', href: 'saqueDeposito', current: false },
  { name: 'Relatorio', href: 'relatorio', current: false },
  { name: 'Usuário da conta', href: 'configuracaoAccount', current: false },
]
const userNavigation = [ 
  { name: 'Configurações', href: '#' },
  { name: 'Sign out', href: '/' },  
]

export default function Home() {

  function classNames(...classes: string[]) {
    return classes.filter(Boolean).join(' ')
  }

  const user = useUser();
  const navigate = useNavigate();
  const [account, setAccount] = useState<TAccount | undefined>(undefined);

  useEffect(() => {    
    api.get(`/conta/${user.user?.user}/buscarConta`)
      .then((response) => {
        setAccount(response.data);
      })
      .catch((error) => {
        console.error("Erro na chamada da API:", error);
      });
  }, [user.user]);


  function logout() {
    user.logout();
    navigate("/")
  }
  
  return (
    <>
      <div className="min-h-full">
        <Disclosure as="nav" className="bg-primary">
          {({ open }) => (
            <>
              <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
                <div className="flex h-16 items-center justify-between">
                  <div className="flex items-center">
                    <div className="flex-shrink-0 text-white font-semibold text-2xl">
                      <a href="/home">MultBanc</a>
                    </div>
                    <div className="hidden md:block">
                      <div className="ml-10 flex items-baseline space-x-4">
                        {navigation.map((item) => (
                          <a
                            key={item.name}
                            href={item.href}
                            onClick={() => item.name === "home" ? navigate("/") : navigate(item.href)}
                            className={classNames(
                              item.current
                                ? 'text-gray-300 hover:bg-gray-700 hover:text-white'
                                : 'text-gray-300 hover:bg-gray-700 hover:text-white',
                              'rounded-md px-3 py-2 text-sm font-medium'
                            )}
                            aria-current={item.current ? 'page' : undefined}
                          >
                            {item.name}
                          </a>
                        ))}

                      </div>
                    </div>
                  </div>
                  <div className="hidden md:block">
                    <div className="ml-4 flex items-center md:ml-6">
                      <button
                        type="button"
                        className="relative rounded-full bg-primary p-1 text-gray-400 hover:text-white focus:outline-none"
                        onClick={() => logout()}
                      >
                        <span className="absolute -inset-1.5" />
                        <span className="sr-only">View notifications</span>
                        <SignOut className="h-6 w-6" aria-hidden="true" />                        
                      </button>                      

                      <Menu as="div" className="relative ml-3">
                        <div>
                          <Menu.Button className="relative flex max-w-xs items-center rounded-full bg-primary text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800">
                            <span className="absolute -inset-1.5" />
                            <span className="sr-only">Open user menu</span>
                            <img className="h-8 w-8 rounded-full" src={user.user?.imgUser} alt="" />
                          </Menu.Button>
                        </div>
                        
                        <Transition
                          as={Fragment}
                          enter="transition ease-out duration-100"
                          enterFrom="transform opacity-0 scale-95"
                          enterTo="transform opacity-100 scale-100"
                          leave="transition ease-in duration-75"
                          leaveFrom="transform opacity-100 scale-100"
                          leaveTo="transform opacity-0 scale-95"
                        >
                          <Menu.Items className="absolute right-0 z-10 mt-2 w-48 origin-top-right rounded-md bg-white py-1 shadow-lg ring-1 ring-pr
                           ring-opacity-5 focus:outline-none">
                            {userNavigation.map((item) => (
                              <Menu.Item key={item.name}>
                                {({ active }) => (
                                  <a
                                    href={item.href}
                                    onClick={() => logout()}
                                    className={classNames(
                                      active ? 'bg-gray-100' : '',
                                      'block px-4 py-2 text-sm text-gray-700'
                                    )}
                                  >
                                    {item.name}
                                  </a>
                                )}
                              </Menu.Item>
                            ))}
                          </Menu.Items>
                        </Transition>
                      </Menu>                     
                    </div>
                  </div>
                  <div className="-mr-2 flex md:hidden">
                    <Disclosure.Button className="relative inline-flex items-center justify-center rounded-md bg-primary p-2 text-gray-400 hover:bg-gray-700 hover:text-white focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800">
                      <span className="absolute -inset-0.5" />
                      <span className="sr-only">Open main menu</span>
                      {open ? (
                        <XMarkIcon className="block h-6 w-6" aria-hidden="true" />
                      ) : (
                        <Bars3Icon className="block h-6 w-6" aria-hidden="true" />
                      )}
                    </Disclosure.Button>                   
                  </div>
                </div>
              </div>

              <Disclosure.Panel className="md:hidden">
                <div className="space-y-1 px-2 pb-3 pt-2 sm:px-3">
                  {navigation.map((item) => (
                    <Disclosure.Button
                      key={item.name}
                      as="a"
                      href={item.href}
                      className={classNames(
                        item.current ? 'bg-primary text-white' : 'text-gray-300 hover:bg-gray-700 hover:text-white',
                        'block rounded-md px-3 py-2 text-base font-medium'
                      )}
                      aria-current={item.current ? 'page' : undefined}
                    >
                      {item.name}
                    </Disclosure.Button>
                  ))}
                </div>
                <div className="border-t border-primary pb-3 pt-4">
                  <div className="flex items-center px-5">
                    <div className="flex-shrink-0">
                      <img className="h-10 w-10 rounded-full" src={user.user?.imgUser} alt="" />
                    </div>
                    <div className="ml-3">
                      <div className="text-base font-medium leading-none text-white">{user.user?.document}</div>
                      <div className="text-sm font-medium leading-none text-gray-400">{user.user?.email}</div>
                    </div>
                    <button
                      type="button"
                      className="relative ml-auto flex-shrink-0 rounded-full bg-gray-800 p-1 text-gray-400 hover:text-white focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800"
                    >
                      <span className="absolute -inset-1.5" />
                      <span className="sr-only">View notifications</span>
                      <BellIcon className="h-6 w-6" aria-hidden="true" />                      
                    </button>
                  </div>
                  <div className="mt-3 space-y-1 px-2">
                    {userNavigation.map((item) => (
                      <Disclosure.Button
                        key={item.name}
                        as="a"
                        href={item.href}
                        className="block rounded-md px-3 py-2 text-base font-medium text-gray-400 hover:bg-gray-700 hover:text-white"
                      >
                        {item.name}
                      </Disclosure.Button>
                    ))}
                  </div>
                </div>
              </Disclosure.Panel>
            </>
          )}
        </Disclosure>
        <header className="shadow-xl bg-white border">
          <div className="grid grid-cols-2 gap-5 items-start mx-auto max-w-6xl px-4 py-6 sm:px-6 lg:px-8">
            <h1 className="bg-primary text-2xl rounded-xl px-3 py-3 font-bold tracking-tigh text-white">Saldo : R${account ? account?.balance.toFixed(2) : "0,00"}</h1>                        
            <h1 className="bg-primary text-2xl rounded-xl px-3 py-3 font-bold tracking-tight text-white">Rendimento : {account ? account?.performace.toFixed(1) : "0,0"}%</h1>
          </div>
        </header>
        <main>
          <div className="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
            <Outlet />
          </div>
        </main>
      </div>
    </>

  );
}



